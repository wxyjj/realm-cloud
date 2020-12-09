package com.example.wxyf.feign;

import com.example.common.enums.StoreType;
import com.example.common.feign.dto.DecryptBatchDto;
import com.example.common.support.Result;
import com.example.wxyf.fallback.PddFallBack;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsQuantityUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsSkuPriceUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsQuantityUpdateResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsSkuPriceUpdateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 拼多多消费
 *
 * @Author wxy
 * @Date 2020/12/7 15:11
 * @Version 1.0
 */
@FeignClient(value = "pdd", fallback = PddFallBack.class)
public interface PddFeign {

    /**
     * 更新价格
     *
     * @param request   request
     * @param storeType storeType
     * @return Object
     */
    @PostMapping(value = "/dataCenter/updatePrice")
    Result<PddGoodsSkuPriceUpdateResponse> updatePrice(@RequestParam(value = "request") PddGoodsSkuPriceUpdateRequest request,
                                                       @RequestParam(value = "storeType") StoreType storeType);

    /**
     * 更新库存
     *
     * @param request   request
     * @param storeType storeType
     * @return Object
     */
    @PostMapping(value = "/dataCenter/updateStock")
    Result<PddGoodsQuantityUpdateResponse> updateStock(@RequestParam(value = "request") PddGoodsQuantityUpdateRequest request,
                                                       @RequestParam(value = "storeType") StoreType storeType);

    /**
     * 订单id获取解密数据
     *
     * @param orderSn 订单id
     * @return Object
     */
    @PostMapping(value = "/dataCenter/decryptBatchByOrderId")
    Result<List<DecryptBatchDto>> decryptBatchByOrderId(@RequestParam(value = "orderSn") String orderSn);

}
