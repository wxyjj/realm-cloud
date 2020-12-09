package com.example.wxyf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author wxy
 * @Date 2020/10/12 9:21
 * @Version 1.0
 */
@Component
@ConfigurationProperties("rsa")
public class RSAProp {

    /**
     * 私匙
     */
    private String privateKey;

    /**
     * 公匙
     */
    private String publicKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
