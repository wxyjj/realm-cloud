package com.example.common.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户信息
 *
 * @Author wxy
 * @Date 2020/9/22 11:07
 * @Version 1.0
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 4135560154825374924L;

    private String id;
    private String username;
    private String password;
    private Boolean status;
    private String clientId;
    private List<String> roles;
}
