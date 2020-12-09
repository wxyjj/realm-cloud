package com.example.hpyf.service;

import com.example.common.dto.CommonDto;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;

/**
 * OMS对接
 *
 * @Author wxy
 * @Date 2020/10/27 14:10
 * @Version 1.0
 */
public interface OmsService {

    /**
     * 更新价格
     *
     * @param commonDto commonDto
     * @return Object
     */
    Boolean updatePrice(CommonDto commonDto);

    /**
     * 更新库存
     *
     * @param commonDto commonDto
     * @return Object
     */
    Boolean updateStock(CommonDto commonDto);

    /**
     * 订单详情查询
     *
     * @param commonDto commonDto
     * @return Object
     */
    CommonDto getOrder(CommonDto commonDto);

    /**
     * 订单配送信息同步（O2O）
     *
     * @param commonDto commonDto
     * @return Object
     */
    Boolean deliveryO2O(CommonDto commonDto);

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
