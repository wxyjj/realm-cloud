package com.example.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id", type = IdType.AUTO)
    @Column(name = "id", columnDefinition = "bigint(20) comment '主键id'")
    private Long id;
    @Column(name = "admin_id", columnDefinition = "varchar(32) comment '用户id'")
    private String adminId;
    @Column(name = "role_id", columnDefinition = "varchar(32) comment '权限id'")
    private String roleId;

}
