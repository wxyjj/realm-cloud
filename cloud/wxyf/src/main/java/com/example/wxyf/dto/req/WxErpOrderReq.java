package com.example.wxyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/11/25 10:04
 * @Version 1.0
 */
@Data
@ApiModel(value = "订单详情查询请求体")
public class WxErpOrderReq implements Serializable {
    private static final long serialVersionUID = 3506709069233790543L;

    @ApiModelProperty(value = "订单号", required = true)
    private String orderId;
}
