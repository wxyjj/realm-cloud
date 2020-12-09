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
@ApiModel(value = "价格更新请求实体")
public class OmsPriceReq implements Serializable {
    private static final long serialVersionUID = 3867562901356531241L;

    @ApiModelProperty(value = "上传的价格列表", required = true)
    private List<OmsPriceReqDataReq> data;
}
