package com.example.pdd.client;

import com.alibaba.fastjson.JSONObject;
import com.example.common.enums.StoreType;
import com.example.common.support.ApiException;
import com.example.pdd.enums.MessageType;
import com.example.pdd.feign.HpyfFeign;
import com.example.pdd.feign.WxyfFeign;
import com.example.pdd.properties.PddProp;
import com.example.pdd.redis.RedisNamespace;
import com.example.pdd.redis.RedisService;
import com.example.pdd.service.PddService;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddOrderInformationGetResponse;
import com.pdd.pop.sdk.message.WsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author wxy
 * @Date 2020/10/29 9:15
 * @Version 1.0
 */
@Slf4j
@Configuration
public class PddClient implements CommandLineRunner {
    //配置
    @Resource
    private PddProp pddProp;
    //拼多多
    @Resource
    private PddService pddService;
    //redis
    @Resource
    private RedisService redisService;
    //消费
    @Resource
    private HpyfFeign hpyfFeign;
    @Resource
    private WxyfFeign wxyfFeign;

    @Override
    public void run(String... args) {
        log.info("拼多多长连接启动!!!");
        WsClient ws = new WsClient(pddProp.getWsAddress(), pddProp.getClientId(), pddProp.getClientSecret(), message -> {
            String json = JSONObject.toJSONString(message);
            log.info("收到消息，消息内容为:" + json);
            Map<?, ?> map = JSONObject.parseObject(json, Map.class);
            //获取消息类型
            MessageType messageType = MessageType.valueOf(map.get("type").toString().toUpperCase());
            //获取消息内容
            Map<?, ?> contentMap = JSONObject.parseObject(map.get("content").toString(), Map.class);
            //获取店铺id
            String mallId = map.get("mallID").toString();
            //获取店铺枚举
            StoreType[] storeTypes = StoreType.values();
            StoreType storeType = Arrays.stream(storeTypes).filter(f -> f.getDescription().equals(mallId)).findFirst().orElseThrow(() -> new ApiException(10000, "不存在的枚举定义"));
            //匹配
            Object id;
            switch (messageType) {
                case PDD_REFUND_REFUNDCLOSED://售后单关闭消息
                    break;
                case PDD_REFUND_REFUNDAGREEAGREEMENT://同意退款协议消息
                    break;
                case PDD_TRADE_TRADECONFIRMED://交易确认消息
                    //订单id
                    id = contentMap.get("tid");
                    //根据店铺区分订单存储
                    this.orderInStore(id.toString(), storeType);
                    break;
                case PDD_TRADE_TRADELOGISTICSADDRESSCHANGED://修改交易收货地址消息
                    break;
                case PDD_TRADE_TRADESUCCESS://交易成功消息
                    break;
                case PDD_TRADE_TRADESELLERSHIP://卖家发货消息
                    break;
                case PDD_GOODS_GOODSONSHELF://商品上架消息
                    //商品id
                    id = contentMap.get("goods_id");
                    //数据接收时间戳
                    Long onShelfTime = Long.valueOf(contentMap.get("onshelf_time").toString());
                    //根据店铺区分商品对码存储
                    this.goodsInStore(Long.valueOf(id.toString()), storeType, onShelfTime, mallId);
                    break;
                case PDD_GOODS_GOODSADD://商品新建消息
                    break;
                case PDD_GOODS_GOODSUPDATE://商品更新消息
                    //商品id
                    id = contentMap.get("goods_id");
                    //数据接收时间戳
                    Long updateTime = Long.valueOf(contentMap.get("update_time").toString());
                    //根据店铺区分商品更新存储
                    this.goodsInStore(Long.valueOf(id.toString()), storeType, updateTime, mallId);
                    break;
                case PDD_CHAT_ORDERPROMISE://订单服务承诺消息
                    break;
                default:
                    throw new ApiException(10000, "暂未处理该通知类型");
            }
        });
        ws.connect();
    }

    /**
     * 根据店铺区分订单存储
     *
     * @param orderId   订单id
     * @param storeType 店铺
     */
    public void orderInStore(String orderId, StoreType storeType) throws Exception {
        //订单详情
        PddOrderInformationGetResponse orderInfo = pddService.orderInformation(orderId, storeType);
        switch (storeType) {
            case HPYF:
                //和平-订单消息入库
                hpyfFeign.saveOrderMessage(orderId);
                //将订单详情存入缓存中，等待批量解密
                redisService.addRedisOrder(orderId, orderInfo, RedisNamespace.PDD_HP_ORDER_DATA);
                break;
            case WXYF:
                //万鑫-订单消息入库
                wxyfFeign.saveOrderMessage(orderId);
                //将订单详情存入缓存中，等待批量解密
                redisService.addRedisOrder(orderId, orderInfo, RedisNamespace.PDD_WX_ORDER_DATA);
                break;
            default:
                break;
        }
    }

    /**
     * 根据店铺区分商品对码存储
     *
     * @param goodsId     商品id
     * @param storeType   店铺
     * @param onShelfTime 数据接收时间戳
     * @param mallId      店铺编码
     */
    public void goodsInStore(Long goodsId, StoreType storeType, Long onShelfTime, String mallId) throws Exception {
        //商品详情
        PddGoodsInformationGetResponse response = pddService.goodsInformation(goodsId, storeType);
        switch (storeType) {
            case HPYF:
                hpyfFeign.goodsCheckCode(response, onShelfTime, mallId);
                break;
            case WXYF:
                wxyfFeign.goodsCheckCode(response, onShelfTime, mallId);
                break;
            default:
                break;
        }
    }
}
