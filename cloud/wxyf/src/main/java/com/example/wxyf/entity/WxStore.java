package com.example.wxyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2020/11/26 9:17
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "wx_store")
@org.hibernate.annotations.Table(appliesTo = "wx_store", comment = "万鑫门店对码")
public class WxStore implements Serializable {
    private static final long serialVersionUID = 6150513075021568599L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "pdd_store_code", columnDefinition = "varchar(255) comment '拼多多门店编码'")
    private String pddStoreCode;
    @Column(name = "wx_store_code", columnDefinition = "varchar(255) comment '万鑫ERP门店编码'")
    private String wxStoreCode;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;
}
