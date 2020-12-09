package com.example.wxyf.config;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

/**
 * 基本配置
 *
 * @Author wxy
 * @Date 2020/9/22 11:07
 * @Version 1.0
 */
@Configuration
public class BasicConfig implements WebMvcConfigurer {

    @Primary
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource(DataSourceProperties properties) {
        HikariDataSource hikariDataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        return new DataSourceProxy(hikariDataSource);
    }

}
