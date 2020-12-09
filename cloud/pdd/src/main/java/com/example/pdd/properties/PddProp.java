package com.example.pdd.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author wxy
 * @Date 2020/10/12 9:21
 * @Version 1.0
 */
@Component
@ConfigurationProperties("pdd")
public class PddProp {

    /**
     * Client_id
     */
    private String clientId;

    /**
     * Client_secret
     */
    private String clientSecret;

    /**
     * Code_Url
     */
    private String codeUrl;

    /**
     * Ws_Address
     */
    private String wsAddress;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getWsAddress() {
        return wsAddress;
    }

    public void setWsAddress(String wsAddress) {
        this.wsAddress = wsAddress;
    }
}
