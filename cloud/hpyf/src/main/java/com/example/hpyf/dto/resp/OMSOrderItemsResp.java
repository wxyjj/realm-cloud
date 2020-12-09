package com.example.hpyf.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/9/21 17:42
 * @Version 1.0
 */
@Data
@ApiModel(value = "OMS订单明细返回体")
public class OMSOrderItemsResp implements Serializable {
    private static final long serialVersionUID = 7520615604700236767L;

    @ApiModelProperty(value = "ERP货品编码")
    private String goodsid;
    @ApiModelProperty(value = "货品名称")
    private String goodsname;
    @ApiModelProperty(value = "货品单价(优惠前价格)")
    private Double price;
    @ApiModelProperty(value = "数量")
    private Integer goodsqty;
    @ApiModelProperty(value = "金额，等于 货品单价乘以数量")
    private Double money;
}
