package com.example.pdd.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.common.enums.StoreType;
import com.example.common.feign.dto.DecryptBatchDto;
import com.example.common.feign.dto.LoginDto;
import com.example.common.support.ApiException;
import com.example.common.utils.CheckUtils;
import com.example.pdd.entiy.PddData;
import com.example.pdd.entiy.PddDecryptBatch;
import com.example.pdd.entiy.PddLoginMessage;
import com.example.pdd.jpa.PddDataRepository;
import com.example.pdd.jpa.PddDecryptBatchRepository;
import com.example.pdd.jpa.PddLoginMessageRepository;
import com.example.pdd.properties.PddProp;
import com.example.pdd.service.PddService;
import com.pdd.pop.sdk.common.util.JsonUtil;
import com.pdd.pop.sdk.http.PopClient;
import com.pdd.pop.sdk.http.PopHttpClient;
import com.pdd.pop.sdk.http.api.pop.request.*;
import com.pdd.pop.sdk.http.api.pop.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 拼多多开放平台
 *
 * @Author wxy
 * @Date 2020/10/20 10:21
 * @Version 1.0
 */
@Slf4j
@Service
public class PddServiceImpl implements PddService {
    //配置
    @Resource
    private PddProp pddProp;
    //jpa
    @Resource
    private PddDataRepository pddDataRepository;
    @Resource
    private PddLoginMessageRepository pddLoginMessageRepository;
    @Resource
    private PddDecryptBatchRepository pddDecryptBatchRepository;

    /**
     * 拼多多接口异常拦截
     */
    private void check(String data) {
        Map<?, ?> map = JSONObject.parseObject(data, Map.class);
        Object object = map.get("error_response");
        if (!ObjectUtils.isEmpty(object)) {
            Map<?, ?> mapError = JSONObject.parseObject(JSONObject.toJSONString(object), Map.class);
            throw new ApiException(10000, mapError.get("error_msg").toString());
        }
    }

    /**
     * 获取授权登录信息
     *
     * @param storeType storeType
     * @return Object
     */
    @Override
    public LoginDto loginMessage(StoreType storeType) {
        LoginDto dto = new LoginDto();
        PddLoginMessage pddLoginMessage = pddLoginMessageRepository.findByOwnerId(storeType.getDescription());
        CheckUtils.checkNull(pddLoginMessage, new ApiException(10000, "该店铺授权已过期，请先授权"));
        BeanUtil.copyProperties(pddLoginMessage, dto);
        return dto;
    }

    /**
     * 订单详情
     *
     * @param orderSn   订单号
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddOrderInformationGetResponse orderInformation(String orderSn, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddOrderInformationGetRequest request = new PddOrderInformationGetRequest();
        request.setOrderSn(orderSn);
        PddOrderInformationGetResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), orderSn, data, new Date()));
        this.check(data);
        return response;
    }

    /**
     * 订单状态
     *
     * @param orderSns  订单号
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddOrderStatusGetResponse orderStatusGet(String orderSns, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddOrderStatusGetRequest request = new PddOrderStatusGetRequest();
        request.setOrderSns(orderSns);
        PddOrderStatusGetResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        this.check(data);
        return response;
    }

    /**
     * 取消发货
     *
     * @param param     param
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddRdcPddgeniusSendgoodsCancelResponse sendGoodsCancel(PddRdcPddgeniusSendgoodsCancelRequest.Param param, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddRdcPddgeniusSendgoodsCancelRequest request = new PddRdcPddgeniusSendgoodsCancelRequest();
        request.setParam(param);
        PddRdcPddgeniusSendgoodsCancelResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        this.check(data);
        return response;
    }

    /**
     * 为已授权的用户开通消息服务
     *
     * @param topics    非必填 消息主题列表，用半角逗号分隔。当用户订阅的topic是应用订阅的子集时才需要设置，不设置表示继承应用所订阅的所有topic，一般情况建议不要设置。
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddPmcUserPermitResponse pmcUserPermit(String topics, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddPmcUserPermitRequest request = new PddPmcUserPermitRequest();
        request.setTopics(topics);
        PddPmcUserPermitResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        this.check(data);
        return response;
    }

    /**
     * 批量解密接口
     * 限制：仅支持云内调用，云外仅支持少量调用供测试使用，云外限流规则1次/10秒
     *
     * @param dataListItem 数据列表
     * @param storeType    storeType
     * @return Object
     */
    @Override
    public PddOpenDecryptBatchResponse decryptBatch(List<PddOpenDecryptBatchRequest.DataListItem> dataListItem, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddOpenDecryptBatchRequest request = new PddOpenDecryptBatchRequest();
        request.setDataList(dataListItem);
        PddOpenDecryptBatchResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        this.check(data);
        return response;
    }

    /**
     * 商品明细
     *
     * @param goodsId   拼多多商品id
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddGoodsDetailGetResponse goodsDetailGet(Long goodsId, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddGoodsDetailGetRequest request = new PddGoodsDetailGetRequest();
        request.setGoodsId(goodsId);
        PddGoodsDetailGetResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        this.check(data);
        return response;
    }

    /**
     * 商品详情
     *
     * @param goodsId   拼多多商品id
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddGoodsInformationGetResponse goodsInformation(Long goodsId, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddGoodsInformationGetRequest request = new PddGoodsInformationGetRequest();
        request.setGoodsId(goodsId);
        PddGoodsInformationGetResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        this.check(data);
        return response;
    }

    /**
     * 更新价格
     *
     * @param request   request
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddGoodsSkuPriceUpdateResponse updatePrice(PddGoodsSkuPriceUpdateRequest request, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddGoodsSkuPriceUpdateResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        check(data);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        return response;
    }

    /**
     * 更新库存
     *
     * @param request   request
     * @param storeType storeType
     * @return Object
     */
    @Override
    public PddGoodsQuantityUpdateResponse updateStock(PddGoodsQuantityUpdateRequest request, StoreType storeType) throws Exception {
        //获取授权登录信息
        LoginDto loginDto = this.loginMessage(storeType);
        //获取accessToken
        String accessToken = loginDto.getAccessToken();
        //拼多多
        PopClient client = new PopHttpClient(pddProp.getClientId(), pddProp.getClientSecret());
        PddGoodsQuantityUpdateResponse response = client.syncInvoke(request, accessToken);
        String data = JsonUtil.transferToJson(response);
        check(data);
        pddDataRepository.save(new PddData(IdUtil.simpleUUID(), JsonUtil.transferToJson(request), data, new Date()));
        return response;
    }

    /**
     * 订单id获取解密数据
     *
     * @param orderSn 订单id
     * @return Object
     */
    @Override
    public List<DecryptBatchDto> decryptBatchByOrderId(String orderSn) {
        List<PddDecryptBatch> list = pddDecryptBatchRepository.findByDataTagOrderByDataTypeAsc(orderSn);
        return list.stream().map(m -> {
            DecryptBatchDto dto = new DecryptBatchDto();
            BeanUtil.copyProperties(m, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
