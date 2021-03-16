package com.example.auth.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基本配置
 *
 * @Author wxy
 * @Date 2020/9/22 11:07
 * @Version 1.0
 */
@Configuration
public class FeignConfig {
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}
