package com.example.hpyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2020/11/9 10:26
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "hp_store_code")
@org.hibernate.annotations.Table(appliesTo = "hp_store_code", comment = "和平门店对码")
public class HpStoreCode implements Serializable {
    private static final long serialVersionUID = -8878420687675805340L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "pdd_store_code", columnDefinition = "varchar(255) comment '拼多多门店编码'")
    private String pddStoreCode;
    @Column(name = "oms_store_code", columnDefinition = "varchar(255) comment 'oms门店编码'")
    private String omsStoreCode;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;
}
