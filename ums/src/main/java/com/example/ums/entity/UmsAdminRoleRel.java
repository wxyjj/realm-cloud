package com.example.ums.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id'")
    private String id;
    @Column(name = "admin_id", columnDefinition = "varchar(32) comment '用户id'")
    private String adminId;
    @Column(name = "role_id", columnDefinition = "varchar(32) comment '权限id'")
    private String roleId;

}
