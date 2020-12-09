package com.example.hpyf.controller;

import com.example.common.support.Result;
import com.example.common.dto.CommonDto;
import com.example.hpyf.service.OmsService;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2020/10/28 13:08
 * @Version 1.0
 */
@RestController
@Api(tags = "和平药房服务提供")
@RequestMapping("/oms")
public class OmsController {
    @Resource
    private OmsService omsService;

    @ApiOperation(value = "价格同步")
    @PostMapping(value = "/updatePrice")
    public Result<Boolean> updatePrice(@RequestBody CommonDto req) {
        return Result.success(omsService.updatePrice(req));
    }

    @ApiOperation(value = "库存同步")
    @PostMapping(value = "/updateStock")
    public Result<Boolean> updateStock(@RequestBody CommonDto req) {
        return Result.success(omsService.updateStock(req));
    }

    @ApiOperation(value = "订单详情查询")
    @PostMapping(value = "/getOrder")
    public Result<CommonDto> getOrder(@RequestBody CommonDto req) {
        return Result.success(omsService.getOrder(req));
    }

    @ApiOperation(value = "订单配送信息同步（O2O）")
    @PostMapping(value = "/deliveryO2O")
    public Result<Boolean> deliveryO2O(@RequestBody CommonDto req) {
        return Result.success(omsService.deliveryO2O(req));
    }

    @ApiOperation(value = "订单消息入库")
    @PostMapping(value = "/saveOrderMessage")
    public Result<Object> saveOrderMessage(@RequestParam(value = "orderId") String orderId) {
        return Result.success(omsService.saveOrderMessage(orderId));
    }

    @ApiOperation(value = "商品对码")
    @PostMapping(value = "/goodsCheckCode")
    public Result<Object> goodsCheckCode(@RequestBody PddGoodsInformationGetResponse pddGoodsInformationGetResponse,
                                         @RequestParam(value = "onShelfTime") Long onShelfTime,
                                         @RequestParam(value = "mallId") String mallId) {
        return Result.success(omsService.goodsCheckCode(pddGoodsInformationGetResponse, onShelfTime, mallId));
    }

}
