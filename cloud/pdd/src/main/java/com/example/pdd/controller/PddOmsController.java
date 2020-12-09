package com.example.pdd.controller;

import com.example.common.enums.StoreType;
import com.example.common.feign.dto.DecryptBatchDto;
import com.example.common.feign.dto.LoginDto;
import com.example.common.support.Result;
import com.example.pdd.service.PddService;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsQuantityUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsSkuPriceUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddOpenDecryptBatchRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddRdcPddgeniusSendgoodsCancelRequest;
import com.pdd.pop.sdk.http.api.pop.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author wxy
 * @Date 2020/10/27 14:13
 * @Version 1.0
 */
@RestController
@Api(tags = "拼多多数据中台")
@RequestMapping("/dataCenter")
public class PddOmsController {
    @Resource
    private PddService pddService;

    @ApiOperation(value = "获取授权登录信息", notes = "获取授权登录信息")
    @PostMapping(value = "/loginMessage")
    public Result<LoginDto> loginMessage(@RequestParam(value = "storeType") StoreType storeType) {
        return Result.success(pddService.loginMessage(storeType));
    }

    @ApiOperation(value = "订单详情", notes = "订单详情")
    @PostMapping(value = "/orderInformation")
    public Result<PddOrderInformationGetResponse> orderInformation(@RequestParam(value = "orderSn") String orderSn,
                                                                   @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.orderInformation(orderSn, storeType));
    }

    @ApiOperation(value = "订单状态", notes = "订单状态")
    @PostMapping(value = "/orderStatusGet")
    public Result<PddOrderStatusGetResponse> orderStatusGet(@RequestParam(value = "orderSns") String orderSns,
                                                            @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.orderStatusGet(orderSns, storeType));
    }

    @ApiOperation(value = "取消发货", notes = "取消发货")
    @PostMapping(value = "/sendGoodsCancel")
    public Result<PddRdcPddgeniusSendgoodsCancelResponse> sendGoodsCancel(@RequestBody PddRdcPddgeniusSendgoodsCancelRequest.Param param,
                                                                          @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.sendGoodsCancel(param, storeType));
    }

    @ApiOperation(value = "为已授权的用户开通消息服务", notes = "为已授权的用户开通消息服务")
    @PostMapping(value = "/pmcUserPermit")
    public Result<PddPmcUserPermitResponse> pmcUserPermit(@RequestParam(value = "topics") String topics,
                                                          @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.pmcUserPermit(topics, storeType));
    }

    @ApiOperation(value = "批量解密接口", notes = "批量解密接口")
    @PostMapping(value = "/decryptBatch")
    public Result<PddOpenDecryptBatchResponse> decryptBatch(@RequestBody List<PddOpenDecryptBatchRequest.DataListItem> dataListItem,
                                                            @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.decryptBatch(dataListItem, storeType));
    }

    @ApiOperation(value = "商品明细", notes = "商品明细")
    @PostMapping(value = "/goodsDetailGet")
    public Result<PddGoodsDetailGetResponse> goodsDetailGet(@RequestParam(value = "goodsId") Long goodsId,
                                                            @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.goodsDetailGet(goodsId, storeType));
    }

    @ApiOperation(value = "商品详情", notes = "商品详情")
    @PostMapping(value = "/goodsInformation")
    public Result<PddGoodsInformationGetResponse> goodsInformation(@RequestParam(value = "goodsId") Long goodsId,
                                                                   @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.goodsInformation(goodsId, storeType));
    }

    @ApiOperation(value = "更新价格", notes = "更新价格")
    @PostMapping(value = "/updatePrice")
    public Result<PddGoodsSkuPriceUpdateResponse> updatePrice(@RequestBody PddGoodsSkuPriceUpdateRequest request,
                                                              @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.updatePrice(request, storeType));
    }

    @ApiOperation(value = "更新库存", notes = "更新库存")
    @PostMapping(value = "/updateStock")
    public Result<PddGoodsQuantityUpdateResponse> updateStock(@RequestBody PddGoodsQuantityUpdateRequest request,
                                                              @RequestParam(value = "storeType") StoreType storeType) throws Exception {
        return Result.success(pddService.updateStock(request, storeType));
    }

    @ApiOperation(value = "订单id获取解密数据", notes = "订单id获取解密数据")
    @PostMapping(value = "/decryptBatchByOrderId")
    public Result<List<DecryptBatchDto>> decryptBatchByOrderId(@RequestParam(value = "orderSn") String orderSn) {
        return Result.success(pddService.decryptBatchByOrderId(orderSn));
    }
}
