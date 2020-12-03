package com.example.hpyf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class HpyfApplication {

    public static void main(String[] args) {
        SpringApplication.run(HpyfApplication.class, args);
    }

}
