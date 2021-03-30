package com.example.ums.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2021/3/30 10:19
 * @Version 1.0
 */
@Configuration
public class JpaConfig {
    @Resource
    private HikariDataSource hikariDataSource;

    @Bean("jpaTransactionManager")
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpa = new JpaTransactionManager();
        jpa.setDataSource(hikariDataSource);
        jpa.setRollbackOnCommitFailure(true);
        return jpa;
    }
}
