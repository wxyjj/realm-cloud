package com.example.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/11/17 10:00
 * @Version 1.0
 */
@Data
@ApiModel(value = "公共体")
public class CommonDto implements Serializable {
    private static final long serialVersionUID = -6534711373695787622L;

    @ApiModelProperty(value = "加密数据", required = true)
    private String encryptData;
}
