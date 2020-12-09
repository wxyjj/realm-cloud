package com.example.hpyf.dto.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/10/28 13:11
 * @Version 1.0
 */
@Data
@ApiModel(value = "OMS订单详情查询请求体")
public class OmsOrderReq   implements Serializable {
    private static final long serialVersionUID = -3504375488358942639L;

    @ApiModelProperty(value = "订单号")
    private String orderid;
}
