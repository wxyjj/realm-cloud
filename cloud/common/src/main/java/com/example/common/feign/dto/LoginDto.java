package com.example.common.feign.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 拼多多授权登录信息
 *
 * @Author wxy
 * @Date 2020/12/7 15:27
 * @Version 1.0
 */
@Data
public class LoginDto implements Serializable {
    private static final long serialVersionUID = 8001999726830018403L;
    /**
     * id
     */
    private String id;
    /**
     * access_token
     */
    private String accessToken;
    /**
     * access_token过期时间点
     */
    private Long expiresAt;
    /**
     * access_token过期时间段，10（表示10秒后过期）
     */
    private Integer expiresIn;
    /**
     * 商家店铺id
     */
    private String ownerId;
    /**
     * 商家账号名称
     */
    private String ownerName;
    /**
     * refresh token，可用来刷新access_token
     */
    private String refreshToken;
    /**
     * refresh token过期时间点
     */
    private Long refreshTokenExpiresAt;
    /**
     * refresh_token过期时间段，10表示10秒后过期
     */
    private Integer refreshTokenExpiresIn;
    /**
     * code值
     */
    private String code;
}
