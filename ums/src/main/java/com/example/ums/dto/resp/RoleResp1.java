package com.example.ums.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2021/3/30 13:52
 * @Version 1.0
 */
@Data
public class RoleResp1 implements Serializable {

    private static final long serialVersionUID = 8258395752507138600L;
    @ApiModelProperty(value = "权限id")
    private String roleId;
    @ApiModelProperty(value = "名称")
    private String name;
}
