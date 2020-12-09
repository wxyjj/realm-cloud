package com.example.wxyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/11/25 9:57
 * @Version 1.0
 */
@Data
@ApiModel(value = "更新的库存列表")
public class WxErpStockDataReq implements Serializable {
    private static final long serialVersionUID = 9025869852021207397L;

    @ApiModelProperty(value = "ERP门店ID", required = true)
    private String erpStoreId;
    @ApiModelProperty(value = "拼多多商品id", required = true)
    private String goodsId;
    @ApiModelProperty(value = "库存修改值。当全量更新库存时，quantity必须为大于等于0的正整数；当增量更新库存时，quantity为整数，可小于等于0。若增量更新时传入的库存为负数，则负数与实际库存之和不能小于0。比如当前实际库存为1，传入增量更新quantity=-1，库存改为0", required = true)
    private String quantity;
    @ApiModelProperty(value = "库存更新方式。1为全量更新，2为增量更新。默认为全量更新", required = true)
    private Integer updateType;
    @ApiModelProperty(value = "拼多多商品sku编码", required = true)
    private String skuId;
}
