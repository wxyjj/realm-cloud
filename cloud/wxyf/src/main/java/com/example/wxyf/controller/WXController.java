package com.example.wxyf.controller;

import com.example.common.dto.CommonDto;
import com.example.common.support.Result;
import com.example.wxyf.service.WXService;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2020/11/25 9:30
 * @Version 1.0
 */
@RestController
@Api(tags = "万鑫药房服务提供")
@RequestMapping("/wx")
public class WXController {
    @Resource
    private WXService wxService;

    @ApiOperation(value = "价格同步")
    @PostMapping("/updatePrice")
    public Result<Boolean> updatePrice(@RequestBody CommonDto req) {
        return Result.success(wxService.updatePrice(req));
    }

    @ApiOperation(value = "库存同步")
    @PostMapping("/updateStock")
    public Result<Boolean> updateStock(@RequestBody CommonDto req) {
        return Result.success(wxService.updateStock(req));
    }

    @ApiOperation(value = "订单详情查询")
    @PostMapping("/getOrder")
    public Result<CommonDto> getOrder(@RequestBody CommonDto req) {
        return Result.success(wxService.getOrder(req));
    }

    @ApiOperation(value = "订单配送信息同步（O2O）")
    @PostMapping("/deliveryO2O")
    public Result<Boolean> deliveryO2O(@RequestBody CommonDto req) {
        return Result.success(wxService.deliveryO2O(req));
    }

    @ApiOperation(value = "订单消息入库")
    @PostMapping(value = "/saveOrderMessage")
    public Result<Object> saveOrderMessage(@RequestParam(value = "orderId") String orderId) {
        return Result.success(wxService.saveOrderMessage(orderId));
    }

    @ApiOperation(value = "商品对码")
    @PostMapping(value = "/goodsCheckCode")
    public Result<Object> goodsCheckCode(@RequestBody PddGoodsInformationGetResponse pddGoodsInformationGetResponse,
                                         @RequestParam(value = "onShelfTime") Long onShelfTime,
                                         @RequestParam(value = "mallId") String mallId) {
        return Result.success(wxService.goodsCheckCode(pddGoodsInformationGetResponse, onShelfTime, mallId));
    }
}
