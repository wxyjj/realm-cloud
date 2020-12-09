package com.example.hpyf;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoDataSourceProxy
@ComponentScan(basePackages = "com.example")
public class HpyfApplication {

    public static void main(String[] args) {
        SpringApplication.run(HpyfApplication.class, args);
    }

}
