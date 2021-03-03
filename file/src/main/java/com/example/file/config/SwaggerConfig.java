package com.example.file.config;

import com.example.common.config.BaseSwaggerConfig;
import com.example.common.properties.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 *
 * @Author wxy
 * @Date 2021/2/8 11:05
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.example.file.controller")
                .title("文件服务")
                .description("文件服务")
                .contactName("文件服务")
                .version("1.0")
                .enableSecurity(Boolean.FALSE)
                .build();
    }
}
