package com.example.auth.fallback;

import com.example.auth.feign.UmsFeign;
import com.example.common.support.Result;
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
    public Result<UserDto> loadUserByUsername(String username) {
        return Result.demotion();
    }
}
