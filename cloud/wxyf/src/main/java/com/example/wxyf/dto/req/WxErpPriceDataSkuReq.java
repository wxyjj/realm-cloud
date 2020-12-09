package com.example.wxyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/11/25 9:46
 * @Version 1.0
 */
@Data
@ApiModel(value = "更新的价格sku列表")
public class WxErpPriceDataSkuReq implements Serializable {
    private static final long serialVersionUID = -1738073263063112551L;
    
    @ApiModelProperty(value = "拼团购买价格（单位分）", required = true)
    private Long groupPrice;
    @ApiModelProperty(value = "单独购买价格（单位分）", required = true)
    private Long singlePrice;
    @ApiModelProperty(value = "拼多多商品sku编码", required = true)
    private String skuId;
}
