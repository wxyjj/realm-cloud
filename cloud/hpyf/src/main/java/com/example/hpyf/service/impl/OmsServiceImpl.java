package com.example.hpyf.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.example.common.dto.CommonDto;
import com.example.common.enums.StoreType;
import com.example.common.feign.dto.DecryptBatchDto;
import com.example.common.support.ApiException;
import com.example.common.utils.CheckUtils;
import com.example.common.utils.RsaUtils;
import com.example.hpyf.dto.req.OmsOrderReq;
import com.example.hpyf.dto.req.OmsPriceReq;
import com.example.hpyf.dto.req.OmsStockReq;
import com.example.hpyf.dto.resp.OMSOrderDetailsResp;
import com.example.hpyf.dto.resp.OMSOrderItemsResp;
import com.example.hpyf.entity.*;
import com.example.hpyf.feign.PddFeign;
import com.example.hpyf.jpa.*;
import com.example.hpyf.properties.RSAProp;
import com.example.hpyf.service.OmsService;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsQuantityUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsSkuPriceUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * OMS对接
 *
 * @Author wxy
 * @Date 2020/10/27 14:10
 * @Version 1.0
 */
@Slf4j
@Service
public class OmsServiceImpl implements OmsService {
    //配置
    @Resource
    private RSAProp rsaProp;
    //工具
    @Resource
    private RsaUtils rsaUtils;
    //jpa
    @Resource
    private HpGoodCodeRepository hpGoodCodeRepository;
    @Resource
    private HpGoodsPriceUpdateRepository hpGoodsPriceUpdateRepository;
    @Resource
    private HpGoodsStockUpdateRepository hpGoodsStockUpdateRepository;
    @Resource
    private HpOrderDetailsRepository hpOrderDetailsRepository;
    @Resource
    private HpOrderItemsRepository hpOrderItemsRepository;
    @Resource
    private HpOrderMessageRepository hpOrderMessageRepository;
    //消费
    @Resource
    private PddFeign pddFeign;

    /**
     * 更新价格
     *
     * @param commonDto commonDto
     * @return Object
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Boolean updatePrice(CommonDto commonDto) {
        CheckUtils.checkNull(commonDto, new ApiException(10000, "参数传入为空"));
        String encryptData = commonDto.getEncryptData();
        CheckUtils.checkNull(encryptData, new ApiException(10000, "加密数据传入为空"));
        //入参数据解密
        RSA rsa = new RSA(rsaProp.getPrivateKey(), rsaProp.getPublicKey());
        OmsPriceReq omsPriceReq = rsaUtils.pubDec(encryptData, OmsPriceReq.class, rsa);
        boolean bool = omsPriceReq.getData()
                .stream()
                .anyMatch(a -> StringUtils.isEmpty(a.getOutSkuId()) || StringUtils.isEmpty(a.getGroupPrice()) || StringUtils.isEmpty(a.getSinglePrice()));
        if (bool) {
            throw new ApiException(10000, "商家商品编码|拼团购买价格(单位元)|单独购买价格（单位元）传入为空");
        }
        omsPriceReq.getData().forEach(c -> {
            //查询通过和平的sku查询对码表获取拼多多的goodsId和skuId
            HpGoodsCode hpGoodsCode = hpGoodCodeRepository.findByOutSkuId(c.getOutSkuId());
            if (ObjectUtils.isEmpty(hpGoodsCode)) {
                throw new ApiException(10000, "商品未对码");
            }
            try {
                PddGoodsSkuPriceUpdateRequest.SkuPriceListItem item = new PddGoodsSkuPriceUpdateRequest.SkuPriceListItem();
                item.setGroupPrice(Long.valueOf(c.getGroupPrice()));
                item.setIsOnsale(c.getIsOnSale());
                item.setSinglePrice(Long.valueOf(c.getSinglePrice()));
                item.setSkuId(Long.valueOf(hpGoodsCode.getPddSkuId()));
                PddGoodsSkuPriceUpdateRequest request = new PddGoodsSkuPriceUpdateRequest();
                request.setGoodsId(Long.valueOf(hpGoodsCode.getPddGoodsId()));
                request.setMarketPrice(Long.parseLong(c.getMarketPrice()) * 100);
                request.setMarketPriceInYuan(c.getMarketPrice());
                request.setSkuPriceList(Collections.singletonList(item));
                request.setSyncGoodsOperate(1);
                pddFeign.updatePrice(request, StoreType.HPYF);
                //更新入库
                HpGoodsPriceUpdate hpGoodsPriceUpdate = new HpGoodsPriceUpdate();
                hpGoodsPriceUpdate.setId(IdUtil.simpleUUID());
                hpGoodsPriceUpdate.setCostPrice(c.getCostPrice());
                hpGoodsPriceUpdate.setMark(2);
                hpGoodsPriceUpdate.setOutSkuId(c.getOutSkuId());
                hpGoodsPriceUpdate.setPlacePointId(c.getPlacePointId());
                hpGoodsPriceUpdate.setPrice(c.getSinglePrice());
                hpGoodsPriceUpdate.setTime(new Date());
                hpGoodsPriceUpdateRepository.save(hpGoodsPriceUpdate);
            } catch (Exception e) {
                throw new ApiException(10000, e.getMessage());
            }
        });
        return null;
    }

    /**
     * 更新库存
     *
     * @param commonDto commonDto
     * @return Object
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Boolean updateStock(CommonDto commonDto) {
        CheckUtils.checkNull(commonDto, new ApiException(10000, "参数传入为空"));
        String encryptData = commonDto.getEncryptData();
        CheckUtils.checkNull(encryptData, new ApiException(10000, "加密数据传入为空"));
        //入参数据解密
        RSA rsa = new RSA(rsaProp.getPrivateKey(), rsaProp.getPublicKey());
        OmsStockReq reqData = rsaUtils.pubDec(encryptData, OmsStockReq.class, rsa);
        boolean bool = reqData.getData()
                .stream()
                .anyMatch(a -> StringUtils.isEmpty(a.getOutSkuId()) || StringUtils.isEmpty(a.getQuantity()));
        if (bool) {
            throw new ApiException(10000, "商家商品编码|库存修改值传入为空");
        }
        reqData.getData().forEach(c -> {
            //查询通过和平的sku查询对码表获取拼多多的goodsId和skuId
            HpGoodsCode hpGoodsCode = hpGoodCodeRepository.findByOutSkuId(c.getOutSkuId());
            if (ObjectUtils.isEmpty(hpGoodsCode)) {
                throw new ApiException(10000, "商品未对码");
            }
            try {
                PddGoodsQuantityUpdateRequest request = new PddGoodsQuantityUpdateRequest();
                request.setGoodsId(Long.valueOf(hpGoodsCode.getPddGoodsId()));
                request.setOuterId(null);
                request.setQuantity(Long.valueOf(c.getQuantity()));
                request.setSkuId(Long.valueOf(hpGoodsCode.getPddSkuId()));
                request.setUpdateType(1);
                pddFeign.updateStock(request, StoreType.HPYF);
                //更新入库
                HpGoodsStockUpdate hpGoodsStockUpdate = new HpGoodsStockUpdate();
                hpGoodsStockUpdate.setId(IdUtil.simpleUUID());
                hpGoodsStockUpdate.setErpShopId(c.getPlacepointId());
                hpGoodsStockUpdate.setOutSkuId(c.getOutSkuId());
                hpGoodsStockUpdate.setMark(2);
                hpGoodsStockUpdate.setGoodsQty(c.getQuantity());
                hpGoodsStockUpdate.setTime(new Date());
                hpGoodsStockUpdate.setNumber(null);
                hpGoodsStockUpdateRepository.save(hpGoodsStockUpdate);
            } catch (Exception e) {
                throw new ApiException(10000, e.getMessage());
            }
        });
        return null;
    }

    /**
     * 订单详情查询
     *
     * @param commonDto commonDto
     * @return Object
     */
    @Override
    public CommonDto getOrder(CommonDto commonDto) {
        CheckUtils.checkNull(commonDto, new ApiException(10000, "参数传入为空"));
        String encryptData = commonDto.getEncryptData();
        CheckUtils.checkNull(encryptData, new ApiException(10000, "加密数据传入为空"));
        //入参数据解密
        RSA rsa = new RSA(rsaProp.getPrivateKey(), rsaProp.getPublicKey());
        OmsOrderReq reqData = rsaUtils.pubDec(encryptData, OmsOrderReq.class, rsa);
        String orderId = reqData.getOrderid();
        //订单详情
        HpOrderDetails hpOrderDetails = hpOrderDetailsRepository.findByOrderId(orderId);
        if (ObjectUtils.isEmpty(hpOrderDetails)) {
            throw new ApiException(10000, "订单不存在");
        }
        //根据订单id获取解密后的收件人、收件人电话，地址等信息
        List<DecryptBatchDto> batchList = pddFeign.decryptBatchByOrderId(orderId).getData();
        //封装返回体
        OMSOrderDetailsResp omsOrderDetailsResp = new OMSOrderDetailsResp();
        if (!CollectionUtils.isEmpty(batchList)) {
            omsOrderDetailsResp.setBuyername(batchList.get(0).getDecryptedData());
            omsOrderDetailsResp.setBuyertelephone(batchList.get(1).getDecryptedData());
            omsOrderDetailsResp.setBuyeraddress(batchList.get(2).getDecryptedData());
        }
        omsOrderDetailsResp.setOrderid(hpOrderDetails.getOrderId());
        omsOrderDetailsResp.setOrderstatus(hpOrderDetails.getOrderStatus());
        omsOrderDetailsResp.setCredate(hpOrderDetails.getCreateDate());
        omsOrderDetailsResp.setPlacepointid(hpOrderDetails.getPlacePointId());
        omsOrderDetailsResp.setPlacepointname(hpOrderDetails.getPlacePointName());
        omsOrderDetailsResp.setSex(null);
        omsOrderDetailsResp.setFreight(hpOrderDetails.getFreight());
        omsOrderDetailsResp.setProvince(hpOrderDetails.getProvince());
        omsOrderDetailsResp.setCity(hpOrderDetails.getCity());
        omsOrderDetailsResp.setDistrict(hpOrderDetails.getDistrict());
        omsOrderDetailsResp.setBuyerLng(null);
        omsOrderDetailsResp.setBuyerLat(null);
        omsOrderDetailsResp.setMemo(hpOrderDetails.getMemo());
        omsOrderDetailsResp.setMoney(hpOrderDetails.getMoney());
        omsOrderDetailsResp.setDiscount(hpOrderDetails.getDiscount());
        omsOrderDetailsResp.setOrder_index(null);
        omsOrderDetailsResp.setSend_time(null);
        omsOrderDetailsResp.setNeed_invoice(2);
        omsOrderDetailsResp.setInvoice_title(null);
        omsOrderDetailsResp.setTaxer_id(null);
        omsOrderDetailsResp.setDrugflag(null);
        omsOrderDetailsResp.setUsedrugname(null);
        omsOrderDetailsResp.setIdentitynumber(null);
        omsOrderDetailsResp.setBirthday(null);
        omsOrderDetailsResp.setPhonenumber(null);
        omsOrderDetailsResp.setPicurl(null);
        omsOrderDetailsResp.setDeliverystationno(4);
        omsOrderDetailsResp.setOrderpaytype(hpOrderDetails.getPayType());
        omsOrderDetailsResp.setUseDrugSex(hpOrderDetails.getUseSex());
        omsOrderDetailsResp.setPrescriptionform(null);
        //根据订单id查询订单明细
        OMSOrderItemsResp resp = new OMSOrderItemsResp();
        HpOrderItems hpOrderItems = hpOrderItemsRepository.findByOrderId(orderId);
        if (!ObjectUtils.isEmpty(hpOrderItems)) {
            resp.setGoodsid(hpOrderItems.getGoodsId());
            resp.setGoodsname(hpOrderItems.getGoodsName());
            resp.setPrice(hpOrderItems.getPrice());
            resp.setGoodsqty(hpOrderItems.getGoodsQty());
            resp.setMoney(hpOrderItems.getPrice() * hpOrderItems.getGoodsQty());
        }
        omsOrderDetailsResp.setGoodsinfo(Collections.singletonList(resp));
        //数据加密并返回
        CommonDto dto = new CommonDto();
        dto.setEncryptData(rsaUtils.pubEnc(omsOrderDetailsResp, rsa));
        return dto;
    }

    /**
     * 订单配送信息同步（O2O）
     *
     * @param commonDto commonDto
     * @return Object
     */
    @Override
    public Boolean deliveryO2O(CommonDto commonDto) {
        CheckUtils.checkNull(commonDto, new ApiException(10000, "参数传入为空"));
        String encryptData = commonDto.getEncryptData();
        CheckUtils.checkNull(encryptData, new ApiException(10000, "加密数据传入为空"));
        return true;
    }

    /**
     * 订单消息入库
     *
     * @param orderId 订单id
     * @return Object
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Object saveOrderMessage(String orderId) {
        HpOrderMessage orderMessage = new HpOrderMessage();
        orderMessage.setId(IdUtil.simpleUUID());
        orderMessage.setOrderId(orderId);
        orderMessage.setOmsState(false);
        hpOrderMessageRepository.save(orderMessage);
        return null;
    }

    /**
     * 商品对码
     *
     * @param pddGoodsInformationGetResponse pddGoodsInformationGetResponse
     * @param onShelfTime                    数据接收时间戳
     * @param mallId                         店铺编码
     * @return Object
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Object goodsCheckCode(PddGoodsInformationGetResponse pddGoodsInformationGetResponse, Long onShelfTime, String mallId) {
        PddGoodsInformationGetResponse.GoodsInfoGetResponseGoodsInfo goodsInfo = pddGoodsInformationGetResponse.getGoodsInfoGetResponse().getGoodsInfo();
        //商品id
        String goodsId = goodsInfo.getGoodsId().toString();
        //商品名称
        String goodsName = goodsInfo.getGoodsName();
        //数据是否存在，存在则删除后新增，否则直接新增
        HpGoodsCode hpGoodsCode = hpGoodCodeRepository.findByPddGoodsId(goodsId);
        if (!ObjectUtils.isEmpty(hpGoodsCode)) {
            hpGoodCodeRepository.delete(hpGoodsCode);
        }
        goodsInfo.getSkuList().forEach(c -> {
            HpGoodsCode goods = new HpGoodsCode();
            goods.setId(IdUtil.simpleUUID());
            goods.setPddGoodsId(goodsId);
            goods.setPddSkuId(c.getSkuId().toString());
            goods.setOutSkuId(c.getOuterId());
            goods.setOutGoodsId(c.getOuterGoodsId());
            goods.setTime(new Date());
            goods.setGoodsName(goodsName);
            goods.setSpecifications(c.getSpec());
            goods.setMallId(mallId);
            goods.setReceivingTime(onShelfTime);
            hpGoodCodeRepository.save(goods);
        });
        return null;
    }
}
