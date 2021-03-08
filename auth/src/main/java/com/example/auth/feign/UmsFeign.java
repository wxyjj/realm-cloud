package com.example.auth.feign;

import com.example.auth.config.BasicConfig;
import com.example.common.support.Result;
import com.example.common.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author wxy
 * @Date 2021/2/8 11:05
 * @Version 1.0
 */
@FeignClient(value = "ums", fallback = UmsFallback.class, configuration = BasicConfig.class)
public interface UmsFeign {

    @GetMapping("/user/loadUserByUsername")
    Result<UserDto> loadUserByUsername(@RequestParam(value = "username") String username);
}
