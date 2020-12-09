package com.example.wxyf.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wxy
 * @Date 2020/11/25 9:45
 * @Version 1.0
 */
@Data
@ApiModel(value = "价格更新请求实体")
public class WxErpPriceReq implements Serializable {
    private static final long serialVersionUID = 5972623327443753230L;

    @ApiModelProperty(value = "更新的价格列表", required = true)
    private List<WxErpPriceDataReq> data;
}
