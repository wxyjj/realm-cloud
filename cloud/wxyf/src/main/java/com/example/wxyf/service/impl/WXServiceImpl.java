package com.example.wxyf.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.example.common.dto.CommonDto;
import com.example.common.enums.StoreType;
import com.example.common.feign.dto.DecryptBatchDto;
import com.example.common.support.ApiException;
import com.example.common.utils.RsaUtils;
import com.example.common.utils.CheckUtils;
import com.example.wxyf.dto.req.*;
import com.example.wxyf.dto.resp.WxErpOrderDetailsResp;
import com.example.wxyf.dto.resp.WxErpOrderItemsResp;
import com.example.wxyf.entity.*;
import com.example.wxyf.feign.PddFeign;
import com.example.wxyf.jpa.*;
import com.example.wxyf.properties.RSAProp;
import com.example.wxyf.service.WXService;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsQuantityUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsSkuPriceUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 万鑫ERP对接
 *
 * @Author wxy
 * @Date 2020/11/25 9:32
 * @Version 1.0
 */
@Service
public class WXServiceImpl implements WXService {
    //配置
    @Resource
    private RSAProp rsaProp;
    //工具
    @Resource
    private RsaUtils rsaUtils;
    //jpa
    @Resource
    private WxGoodsPriceUpdateRepository wxGoodsPriceUpdateRepository;
    @Resource
    private WxGoodsStockUpdateRepository wxGoodsStockUpdateRepository;
    @Resource
    private WxOrderDetailsRepository wxOrderDetailsRepository;
    @Resource
    private WxOrderItemsRepository wxOrderItemsRepository;
    @Resource
    private WxOrderMessageRepository wxOrderMessageRepository;
    @Resource
    private WxGoodsRepository wxGoodsRepository;
    //消费
    @Resource
    private PddFeign pddFeign;

    /**
     * 价格同步
     */
    @Override
        @GlobalTransactional(rollbackFor = Exception.class)
    public Boolean updatePrice(CommonDto req) {
        CheckUtils.checkNull(req, new ApiException(10000, "对象不能为空"));
        CheckUtils.checkNull(req.getEncryptData(), new ApiException(10000, "加密数据不能为空"));
        RSA rsa = new RSA(rsaProp.getPrivateKey(), rsaProp.getPublicKey());
        WxErpPriceReq priceReq = rsaUtils.pubDec(req.getEncryptData(), WxErpPriceReq.class, rsa);
        boolean bool = priceReq.getData().stream().anyMatch(a -> a.getSkuData().isEmpty() || a.getSkuData().size() == 0);
        if (bool) {
            throw new ApiException(10000, "没有产品列表");
        }
        List<WxErpPriceDataReq> list = priceReq.getData();
        for (WxErpPriceDataReq wxErpPriceDataReq : list) {
            bool = wxErpPriceDataReq.getSkuData().stream().anyMatch(a -> StringUtils.isEmpty(a.getSkuId()) || StringUtils.isEmpty(a.getGroupPrice()) || StringUtils.isEmpty(a.getSinglePrice()));
            if (bool) {
                throw new ApiException(10000, "拼多多商品sku编码|单独购买价格（单位分）|拼团购买价格（单位分）传入为空");
            }
        }
        list.forEach(c -> {
            try {
                List<PddGoodsSkuPriceUpdateRequest.SkuPriceListItem> listItems = new ArrayList<>();
                c.getSkuData().forEach(cc -> {
                    PddGoodsSkuPriceUpdateRequest.SkuPriceListItem item = new PddGoodsSkuPriceUpdateRequest.SkuPriceListItem();
                    item.setIsOnsale(1);
                    item.setSkuId(Long.valueOf(cc.getSkuId()));
                    item.setGroupPrice(cc.getGroupPrice());
                    item.setSinglePrice(cc.getSinglePrice());
                    listItems.add(item);
                });
                PddGoodsSkuPriceUpdateRequest request = new PddGoodsSkuPriceUpdateRequest();
                request.setSyncGoodsOperate(1);
                request.setGoodsId(Long.valueOf(c.getGoodsId()));
                request.setMarketPrice(null);
                request.setMarketPriceInYuan(null);
                request.setSkuPriceList(listItems);
                pddFeign.updatePrice(request, StoreType.WXYF);
                //更新入库
                c.getSkuData().forEach(ccc -> {
                    WxGoodsPriceUpdate wxGoodsPriceUpdate = new WxGoodsPriceUpdate();
                    wxGoodsPriceUpdate.setId(IdUtil.simpleUUID());
                    wxGoodsPriceUpdate.setMark(2);
                    wxGoodsPriceUpdate.setTime(new Date());
                    wxGoodsPriceUpdate.setPddGoodsId(c.getGoodsId());
                    wxGoodsPriceUpdate.setPddSkuId(ccc.getSkuId());
                    wxGoodsPriceUpdate.setGroupPrice(ccc.getGroupPrice().toString());
                    wxGoodsPriceUpdate.setSinglePrice(ccc.getSinglePrice().toString());
                    wxGoodsPriceUpdate.setWxStoreCode(c.getErpStoreId());
                    wxGoodsPriceUpdateRepository.save(wxGoodsPriceUpdate);
                });
            } catch (Exception e) {
                throw new ApiException(10000, e.getMessage());
            }
        });
        return null;
    }

    /**
     * 库存同步
     */
    @Override
        @GlobalTransactional(rollbackFor = Exception.class)
    public Boolean updateStock(CommonDto req) {
        CheckUtils.checkNull(req, new ApiException(10000, "对象不能为空"));
        CheckUtils.checkNull(req.getEncryptData(), new ApiException(10000, "加密数据不能为空"));
        RSA rsa = new RSA(rsaProp.getPrivateKey(), rsaProp.getPublicKey());
        WxErpStockReq stockReq = rsaUtils.pubDec(req.getEncryptData(), WxErpStockReq.class, rsa);
        boolean bool = stockReq.getData().stream().anyMatch(a -> StringUtils.isEmpty(a.getSkuId()) || StringUtils.isEmpty(a.getErpStoreId()) || StringUtils.isEmpty(a.getGoodsId()) || StringUtils.isEmpty(a.getUpdateType()));
        if (bool) {
            throw new ApiException(10000, "ERP门店ID|拼多多商品id|库存更新方式|拼多多商品sku编码传入为空");
        }
        stockReq.getData().forEach(c -> {
            try {
                PddGoodsQuantityUpdateRequest request = new PddGoodsQuantityUpdateRequest();
                request.setOuterId(null);
                request.setGoodsId(Long.valueOf(c.getGoodsId()));
                request.setQuantity(Long.valueOf(c.getQuantity()));
                request.setSkuId(Long.valueOf(c.getSkuId()));
                request.setUpdateType(c.getUpdateType());
                pddFeign.updateStock(request, StoreType.WXYF);
                //更新入库
                WxGoodsStockUpdate wxGoodsStockUpdate = new WxGoodsStockUpdate();
                wxGoodsStockUpdate.setId(IdUtil.simpleUUID());
                wxGoodsStockUpdate.setWxStoreCode(c.getErpStoreId());
                wxGoodsStockUpdate.setPddGoodsId(c.getGoodsId());
                wxGoodsStockUpdate.setPddSkuId(c.getSkuId());
                wxGoodsStockUpdate.setMark(2);
                wxGoodsStockUpdate.setQuantity(c.getQuantity());
                wxGoodsStockUpdate.setTime(new Date());
                wxGoodsStockUpdate.setUpdateType(c.getUpdateType());
                wxGoodsStockUpdateRepository.save(wxGoodsStockUpdate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return null;
    }

    /**
     * 订单详情查询
     */
    @Override
    public CommonDto getOrder(CommonDto req) {
        CheckUtils.checkNull(req, new ApiException(10000, "对象不能为空"));
        CheckUtils.checkNull(req.getEncryptData(), new ApiException(10000, "加密数据不能为空"));
        RSA rsa = new RSA(rsaProp.getPrivateKey(), rsaProp.getPublicKey());
        WxErpOrderReq orderReq = rsaUtils.pubDec(req.getEncryptData(), WxErpOrderReq.class, rsa);
        CheckUtils.checkNull(orderReq.getOrderId(), new ApiException(10000, "订单号不能为空"));
        // 订单详情
        WxOrderDetails orderDetails = wxOrderDetailsRepository.findByOrderId(orderReq.getOrderId());
        if (ObjectUtils.isEmpty(orderDetails)) {
            throw new ApiException(10000, "订单不存在");
        }
        //根据订单id获取解密后的收件人、收件人电话，地址等信息
        List<DecryptBatchDto> batchList = pddFeign.decryptBatchByOrderId(orderReq.getOrderId()).getData();
        // 封装返回体
        WxErpOrderDetailsResp orderDetailsResp = new WxErpOrderDetailsResp();
        if (!CollectionUtils.isEmpty(batchList)) {
            orderDetailsResp.setBuyername(batchList.get(0).getDecryptedData());
            orderDetailsResp.setBuyeraddress(batchList.get(2).getDecryptedData());
            orderDetailsResp.setBuyertelephone(batchList.get(1).getDecryptedData());
        }
        orderDetailsResp.setSex(null);
        orderDetailsResp.setPicurl(null);
        orderDetailsResp.setBuyerLng(null);
        orderDetailsResp.setBuyerLat(null);
        orderDetailsResp.setTaxer_id(null);
        orderDetailsResp.setDrugflag(null);
        orderDetailsResp.setBirthday(null);
        orderDetailsResp.setSend_time(null);
        orderDetailsResp.setOrder_index(null);
        orderDetailsResp.setUsedrugname(null);
        orderDetailsResp.setPhonenumber(null);
        orderDetailsResp.setInvoice_title(null);
        orderDetailsResp.setIdentitynumber(null);
        orderDetailsResp.setPrescriptionform(null);
        orderDetailsResp.setOrderid(orderDetails.getOrderId());
        orderDetailsResp.setCredate(orderDetails.getCreateDate());
        orderDetailsResp.setOrderstatus(orderDetails.getOrderStatus());
        orderDetailsResp.setPlacepointname(orderDetails.getPlacePointName());
        orderDetailsResp.setPlacepointid(orderDetails.getPlacePointId());
        orderDetailsResp.setFreight(orderDetails.getFreight());
        orderDetailsResp.setProvince(orderDetails.getProvince());
        orderDetailsResp.setCity(orderDetails.getCity());
        orderDetailsResp.setDistrict(orderDetails.getDistrict());
        orderDetailsResp.setMemo(orderDetails.getMemo());
        orderDetailsResp.setMoney(orderDetails.getMoney());
        orderDetailsResp.setDiscount(orderDetails.getDiscount());
        orderDetailsResp.setNeed_invoice(2);
        orderDetailsResp.setDeliverystationno(4);
        orderDetailsResp.setOrderpaytype(orderDetails.getPayType());
        orderDetailsResp.setUseDrugSex(orderDetails.getUseSex());
        // 根据订单id查询订单明细
        WxErpOrderItemsResp orderItemsResp = new WxErpOrderItemsResp();
        WxOrderItems orderItems = wxOrderItemsRepository.findByOrderId(orderReq.getOrderId());
        if (!ObjectUtils.isEmpty(orderItems)) {
            orderItemsResp.setPrice(orderItems.getPrice());
            orderItemsResp.setGoodsid(orderItems.getGoodsId());
            orderItemsResp.setGoodsqty(orderItems.getGoodsQty());
            orderItemsResp.setGoodsname(orderItems.getGoodsName());
            orderItemsResp.setMoney(orderItems.getPrice() * orderItems.getGoodsQty());
        }
        orderDetailsResp.setGoodsinfo(Collections.singletonList(orderItemsResp));
        //加密返回
        CommonDto dto = new CommonDto();
        dto.setEncryptData(rsaUtils.pubEnc(orderDetailsResp, rsa));
        return dto;
    }

    /**
     * 订单配送信息同步（O2O）
     */
    @Override
    public Boolean deliveryO2O(CommonDto req) {
        CheckUtils.checkNull(req, new ApiException(10000, "对象不能为空"));
        CheckUtils.checkNull(req.getEncryptData(), new ApiException(10000, "加密数据不能为空"));
        RSA rsa = new RSA(rsaProp.getPrivateKey(), rsaProp.getPublicKey());
        WxErpDeliveryO2OReq deliveryO2OReq = rsaUtils.pubDec(req.getEncryptData(), WxErpDeliveryO2OReq.class, rsa);
        CheckUtils.checkNull(deliveryO2OReq.getOrderId(), new ApiException(10000, "订单号不能为空"));
        CheckUtils.checkNull(deliveryO2OReq.getIsCheck(), new ApiException(10000, "是否签收不能为空"));
        CheckUtils.checkNull(deliveryO2OReq.getName(), new ApiException(10000, "骑手姓名不能为空"));
        CheckUtils.checkNull(deliveryO2OReq.getTel(), new ApiException(10000, "骑手联系电话不能为空"));
        return null;
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
        WxOrderMessage orderMessage = new WxOrderMessage();
        orderMessage.setId(IdUtil.simpleUUID());
        orderMessage.setOrderId(orderId);
        orderMessage.setState(false);
        wxOrderMessageRepository.save(orderMessage);
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
        WxGoods wxGoods = wxGoodsRepository.findByPddGoodsId(goodsId);
        if (!ObjectUtils.isEmpty(wxGoods)) {
            wxGoodsRepository.delete(wxGoods);
        }
        goodsInfo.getSkuList().forEach(c -> {
            WxGoods goods = new WxGoods();
            goods.setId(IdUtil.simpleUUID());
            goods.setMallId(mallId);
            goods.setTime(new Date());
            goods.setPddGoodsId(goodsId);
            goods.setGoodsName(goodsName);
            goods.setOutSkuId(c.getOuterId());
            goods.setReceivingTime(onShelfTime);
            goods.setSpecifications(c.getSpec());
            goods.setOutGoodsId(c.getOuterGoodsId());
            goods.setPddSkuId(c.getSkuId().toString());
            wxGoodsRepository.save(goods);
        });
        return null;
    }
}
