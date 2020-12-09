package com.example.wxyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wxy
 * @Date 2020/11/25 9:46
 * @Version 1.0
 */
@Data
@ApiModel(value = "更新的价格列表")
public class WxErpPriceDataReq implements Serializable {
    private static final long serialVersionUID = 338316953682261810L;

    @ApiModelProperty(value = "拼多多商品id", required = true)
    private String goodsId;

    @ApiModelProperty(value = "更新的价格sku列表", required = true)
    private List<WxErpPriceDataSkuReq> skuData;

    @ApiModelProperty(value = "ERP门店ID", required = true)
    private String erpStoreId;
}
