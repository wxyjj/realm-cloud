package com.example.auth.feign;

import com.example.common.user.UserDto;
import org.springframework.stereotype.Component;

/**
 * @Author wxy
 * @Date 2021/3/5 16:47
 * @Version 1.0
 */
@Component
public class UmsFallback implements UmsFeign {

    @Override
    public UserDto loadUserByUsername(String username) {
        return null;
    }
}
