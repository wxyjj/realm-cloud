package com.example.wxyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/11/16 14:30
 * @Version 1.0
 */
@Data
@ApiModel(value = "订单配送信息同步（O2O）")
public class WxErpDeliveryO2OReq implements Serializable {
    private static final long serialVersionUID = -8649306193453755244L;

    @ApiModelProperty(value = "订单号", required = true)
    private String orderId;
    @ApiModelProperty(value = "是否签收 true是 false否", required = true)
    private Boolean isCheck;
    @ApiModelProperty(value = "骑手姓名", required = true)
    private String name;
    @ApiModelProperty(value = "骑手联系电话", required = true)
    private String tel;
    @ApiModelProperty(value = "骑手当前坐标（高德坐标系）")
    private String coordinate;
}
