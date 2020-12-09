package com.example.pdd.feign;

import com.example.common.support.Result;
import com.example.pdd.fallback.WxyfFallBack;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 和平药房消费
 *
 * @Author wxy
 * @Date 2020/12/7 15:11
 * @Version 1.0
 */
@FeignClient(value = "wxyf", fallback = WxyfFallBack.class)
public interface WxyfFeign {

    /**
     * 订单消息入库
     *
     * @param orderId 订单id
     * @return Object
     */
    @PostMapping(value = "/wx/saveOrderMessage")
    Result<Object> saveOrderMessage(@RequestParam(value = "orderId") String orderId);

    /**
     * 商品对码
     *
     * @param pddGoodsInformationGetResponse pddGoodsInformationGetResponse
     * @param onShelfTime                    数据接收时间戳
     * @param mallId                         店铺编码
     * @return Object
     */
    @PostMapping(value = "/wx/goodsCheckCode")
    Result<Object> goodsCheckCode(@RequestBody PddGoodsInformationGetResponse pddGoodsInformationGetResponse,
                                  @RequestParam(value = "onShelfTime") Long onShelfTime,
                                  @RequestParam(value = "mallId") String mallId);
}
