package com.example.ums.service;

import com.example.common.user.UserDto;

/**
 * @Author wxy
 * @Date 2021/3/16 16:03
 * @Version 1.0
 */
public interface UmsService {

    /**
     * 根据用户名获取通用用户信息
     */
    UserDto loadUserByUsername(String username);
}
