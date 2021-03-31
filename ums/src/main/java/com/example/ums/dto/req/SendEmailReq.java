package com.example.ums.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2021/3/31 14:13
 * @Version 1.0
 */
@Data
public class SendEmailReq implements Serializable {

    private static final long serialVersionUID = -7999676080553933314L;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "邮箱")
    private String email;
}
