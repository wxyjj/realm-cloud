package com.example.hpyf.dto.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yofree
 */
@Data
@ApiModel(value = "库存更新请求类")
public class OmsStockReq implements Serializable {
    private static final long serialVersionUID = -5770135715297604931L;

    @ApiModelProperty(value = "库存列表", required = true)
    private List<OmsStockReqDataReq> data;
}
