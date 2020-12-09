package com.example.wxyf.service;


import com.example.common.dto.CommonDto;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;

/**
 * 万鑫ERP对接
 *
 * @Author wxy
 * @Date 2020/11/25 9:32
 * @Version 1.0
 */
public interface WXService {
    /**
     * 价格同步
     */
    Boolean updatePrice(CommonDto req);

    /**
     * 库存同步
     */
    Boolean updateStock(CommonDto req);

    /**
     * 订单详情查询
     */
    CommonDto getOrder(CommonDto req);

    /**
     * 订单配送信息同步（O2O）
     */
    Boolean deliveryO2O(CommonDto req);

    /**
     * 订单消息入库
     *
     * @param orderId 订单id
     * @return Object
     */
    Object saveOrderMessage(String orderId);

    /**
     * 商品对码
     *
     * @param pddGoodsInformationGetResponse pddGoodsInformationGetResponse
     * @param onShelfTime                    数据接收时间戳
     * @param mallId                         店铺编码
     * @return Object
     */
    Object goodsCheckCode(PddGoodsInformationGetResponse pddGoodsInformationGetResponse, Long onShelfTime, String mallId);
}
