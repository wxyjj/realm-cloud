package com.example.pdd.service;

import com.example.common.enums.StoreType;
import com.example.common.feign.dto.DecryptBatchDto;
import com.example.common.feign.dto.LoginDto;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsQuantityUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsSkuPriceUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddOpenDecryptBatchRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddRdcPddgeniusSendgoodsCancelRequest;
import com.pdd.pop.sdk.http.api.pop.response.*;

import java.util.List;

/**
 * 拼多多开放平台
 *
 * @Author wxy
 * @Date 2020/10/20 10:21
 * @Version 1.0
 */
public interface PddService {

    /**
     * 获取授权登录信息
     *
     * @param storeType storeType
     * @return Object
     */
    LoginDto loginMessage(StoreType storeType);

    /**
     * 订单详情
     *
     * @param orderSn   订单号
     * @param storeType storeType
     * @return Object
     */
    PddOrderInformationGetResponse orderInformation(String orderSn, StoreType storeType) throws Exception;

    /**
     * 订单状态
     *
     * @param orderSns  订单号
     * @param storeType storeType
     * @return Object
     */
    PddOrderStatusGetResponse orderStatusGet(String orderSns, StoreType storeType) throws Exception;

    /**
     * 取消发货
     *
     * @param param     param
     * @param storeType storeType
     * @return Object
     */
    PddRdcPddgeniusSendgoodsCancelResponse sendGoodsCancel(PddRdcPddgeniusSendgoodsCancelRequest.Param param, StoreType storeType) throws Exception;

    /**
     * 为已授权的用户开通消息服务
     *
     * @param topics    非必填 消息主题列表，用半角逗号分隔。当用户订阅的topic是应用订阅的子集时才需要设置，不设置表示继承应用所订阅的所有topic，一般情况建议不要设置。
     * @param storeType storeType
     * @return Object
     */
    PddPmcUserPermitResponse pmcUserPermit(String topics, StoreType storeType) throws Exception;

    /**
     * 批量解密接口
     * 限制：仅支持云内调用，云外仅支持少量调用供测试使用，云外限流规则1次/10秒
     *
     * @param dataListItem 数据列表
     * @param storeType    storeType
     * @return Object
     */
    PddOpenDecryptBatchResponse decryptBatch(List<PddOpenDecryptBatchRequest.DataListItem> dataListItem, StoreType storeType) throws Exception;

    /**
     * 商品明细
     *
     * @param goodsId   拼多多商品id
     * @param storeType storeType
     * @return Object
     */
    PddGoodsDetailGetResponse goodsDetailGet(Long goodsId, StoreType storeType) throws Exception;

    /**
     * 商品详情
     *
     * @param goodsId   拼多多商品id
     * @param storeType storeType
     * @return Object
     */
    PddGoodsInformationGetResponse goodsInformation(Long goodsId, StoreType storeType) throws Exception;

    /**
     * 更新价格
     *
     * @param request   request
     * @param storeType storeType
     * @return Object
     */
    PddGoodsSkuPriceUpdateResponse updatePrice(PddGoodsSkuPriceUpdateRequest request, StoreType storeType) throws Exception;

    /**
     * 更新库存
     *
     * @param request   request
     * @param storeType storeType
     * @return Object
     */
    PddGoodsQuantityUpdateResponse updateStock(PddGoodsQuantityUpdateRequest request, StoreType storeType) throws Exception;

    /**
     * 订单id获取解密数据
     *
     * @param orderSn 订单id
     * @return Object
     */
    List<DecryptBatchDto> decryptBatchByOrderId(String orderSn);
}
