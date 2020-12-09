package com.example.hpyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "上传的价格列表")
public class OmsPriceReqDataReq implements Serializable {
    private static final long serialVersionUID = -5129887830728778158L;

    @ApiModelProperty(value = "erp的skuId", required = true)
    private String outSkuId;
    @ApiModelProperty(value = "ERP门店ID")
    private String placePointId;
    @ApiModelProperty(value = "市场价(单位元)")
    private String marketPrice;
    @ApiModelProperty(value = "拼团购买价格(单位元)", required = true)
    private String groupPrice;
    @ApiModelProperty(value = "sku上架状态，0-已下架，1-上架中")
    private Integer isOnSale;
    @ApiModelProperty(value = "单独购买价格（单位元）", required = true)
    private String singlePrice;
    @ApiModelProperty(value = "提交后上架状态，0:上架,1:保持原样")
    private Integer syncGoodsOperate;
    @ApiModelProperty(value = "货品成本价")
    private String costPrice;


}
