package com.example.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2021/3/16 16:10
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "ums_admin")
@org.hibernate.annotations.Table(appliesTo = "ums_admin", comment = "用户")
public class UmsAdmin implements Serializable {

    private static final long serialVersionUID = -5102187452127152102L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    @Column(name = "id", columnDefinition = "bigint(20) comment '主键id'")
    private Long id;
    @Column(name = "admin_id", columnDefinition = "varchar(32) comment 'admin_id'")
    private String adminId;
    @Column(name = "username", columnDefinition = "varchar(50) comment '姓名'")
    private String username;
    @Column(name = "password", columnDefinition = "varchar(100) comment '密码'")
    private String password;
    @Column(name = "icon", columnDefinition = "varchar(255) comment '头像URL'")
    private String icon;
    @Column(name = "email", columnDefinition = "varchar(50) comment '邮箱'")
    private String email;
    @Column(name = "nick_name", columnDefinition = "varchar(50) comment '昵称'")
    private String nickName;
    @Column(name = "note", columnDefinition = "varchar(255) comment '备注信息'")
    private String note;
    @Column(name = "create_time", columnDefinition = "datetime(3) comment '创建时间'")
    private Date createTime;
    @Column(name = "login_time", columnDefinition = "datetime(3) comment '最后登录时间'")
    private Date loginTime;
    @Column(name = "status", columnDefinition = "tinyint(1) comment '帐号启用状态：0->禁用；1->启用'")
    private Boolean status;
}
