package com.example.wxyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wxy
 * @Date 2020/11/25 9:57
 * @Version 1.0
 */
@Data
@ApiModel(value = "库存更新请求实体")
public class WxErpStockReq implements Serializable {
    private static final long serialVersionUID = -7713305684133233673L;

    @ApiModelProperty(value = "更新的库存列表", required = true)
    private List<WxErpStockDataReq> data;
}
