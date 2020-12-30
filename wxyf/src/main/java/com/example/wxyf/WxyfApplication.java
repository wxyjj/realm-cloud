package com.example.wxyf;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoDataSourceProxy
@ComponentScan(basePackages = "com.example")
public class WxyfApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxyfApplication.class, args);
    }

}



