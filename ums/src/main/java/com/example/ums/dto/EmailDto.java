package com.example.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2021/3/30 16:05
 * @Version 1.0
 */
@Data
public class EmailDto implements Serializable {

    private static final long serialVersionUID = 1709624039441867793L;
    @ApiModelProperty(value = "消息id")
    private String msgId;
    @ApiModelProperty(value = "验证码")
    private Integer code;
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    @ApiModelProperty(value = "邮件主题")
    private String subject;
    @ApiModelProperty(value = "邮件内容")
    private String content;
}
