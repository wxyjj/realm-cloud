package com.example.ums.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author wxy
 * @Date 2021/3/30 10:19
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.example.ums.mapper")
@EntityScan(basePackages = "com.example.ums.entity")
public class MybatisConfig {
}
