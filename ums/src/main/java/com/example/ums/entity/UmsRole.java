package com.example.ums.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2021/3/16 16:28
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "ums_role")
@org.hibernate.annotations.Table(appliesTo = "ums_role", comment = "用户-权限")
public class UmsRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id'")
    private String id;
    @Column(name = "name", columnDefinition = "varchar(255) comment '名称'")
    private String name;
    @Column(name = "description", columnDefinition = "varchar(255) comment '描述'")
    private String description;
    @Column(name = "create_time", columnDefinition = "datetime(3) comment '创建时间'")
    private Date createTime;
    @Column(name = "status", columnDefinition = "tinyint(1) comment '启用状态：0->禁用；1->启用'")
    private Boolean status;
    @Column(name = "sort", columnDefinition = "integer(11) comment '排序'")
    private Integer sort;
}
