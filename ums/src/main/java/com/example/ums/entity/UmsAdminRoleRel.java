package com.example.ums.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2021/3/16 16:10
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "ums_admin_role_rel")
@org.hibernate.annotations.Table(appliesTo = "ums_admin_role_rel", comment = "用户-权限关联")
public class UmsAdminRoleRel implements Serializable {

    private static final long serialVersionUID = 5297398968811703278L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "integer(11) comment '主键id'")
    private Integer id;
    @Column(name = "admin_id", columnDefinition = "varchar(32) comment '用户id'")
    private String adminId;
    @Column(name = "role_id", columnDefinition = "varchar(32) comment '权限id'")
    private String roleId;

}
