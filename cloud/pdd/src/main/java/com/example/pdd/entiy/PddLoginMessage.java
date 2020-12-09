package com.example.pdd.entiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/10/27 14:30
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "pdd_login_message")
@org.hibernate.annotations.Table(appliesTo = "pdd_login_message", comment = "拼多多登录信息")
public class PddLoginMessage implements Serializable {
    private static final long serialVersionUID = -4372636104369859843L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "access_token", columnDefinition = "varchar(255) comment 'access_token'")
    private String accessToken;
    @Column(name = "expires_at", columnDefinition = "bigint(20) comment 'access_token过期时间点'")
    private Long expiresAt;
    @Column(name = "expires_in", columnDefinition = "int(11) comment 'access_token过期时间段，10（表示10秒后过期）'")
    private Integer expiresIn;
    @Column(name = "owner_id", columnDefinition = "varchar(255) comment '商家店铺id'")
    private String ownerId;
    @Column(name = "owner_name", columnDefinition = "varchar(255) comment '商家账号名称'")
    private String ownerName;
    @Column(name = "refresh_token", columnDefinition = "varchar(255) comment 'refresh token，可用来刷新access_token'")
    private String refreshToken;
    @Column(name = "refresh_token_expires_at", columnDefinition = "bigint(20) comment 'refresh token过期时间点'")
    private Long refreshTokenExpiresAt;
    @Column(name = "refresh_token_expires_in", columnDefinition = "int(11) comment 'refresh_token过期时间段，10表示10秒后过期'")
    private Integer refreshTokenExpiresIn;
    @Column(name = "code", columnDefinition = "varchar(255) comment 'code值'")
    private String code;
}
