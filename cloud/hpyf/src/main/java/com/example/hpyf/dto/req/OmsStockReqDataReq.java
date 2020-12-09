package com.example.hpyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yofree
 */
@Data
@ApiModel(value = "库存列表")
public class OmsStockReqDataReq implements Serializable {
    private static final long serialVersionUID = -9112447563336004626L;

    @ApiModelProperty(value = "商家商品编码", required = true)
    private String outSkuId;
    @ApiModelProperty(value = "库存修改值。当全量更新库存时，quantity必须为大于等于0的正整数；当增量更新库存时，quantity为整数，可小于等于0。若增量更新时传入的库存为负数，则负数与实际库存之和不能小于0。比如当前实际库存为1，传入增量更新quantity=-1，库存改为0", required = true)
    private String quantity;
    @ApiModelProperty(value = "库存更新方式，可选。1为全量更新，2为增量更新。如果不填，默认为全量更新")
    private Integer updateType;
    @ApiModelProperty(value = "ERP门店ID")
    private String placepointId;

}
