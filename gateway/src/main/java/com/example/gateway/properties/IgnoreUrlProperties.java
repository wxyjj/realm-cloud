package com.example.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wxy
 * @Date 2020/10/12 9:21
 * @Version 1.0
 */
@Component
@ConfigurationProperties("ignore")
public class IgnoreUrlProperties implements Serializable {
    private static final long serialVersionUID = -360302441552011793L;

    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
