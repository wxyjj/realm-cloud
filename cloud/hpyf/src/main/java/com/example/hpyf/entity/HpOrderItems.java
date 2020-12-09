package com.example.hpyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/9/21 17:42
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "hp_order_items")
@org.hibernate.annotations.Table(appliesTo = "hp_order_items", comment = "和平订单明细")
public class HpOrderItems implements Serializable {
    private static final long serialVersionUID = -2984916682703247409L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "goods_id", columnDefinition = "varchar(255) comment 'ERP货品编码'")
    private String goodsId;
    @Column(name = "goods_name", columnDefinition = "varchar(255) comment '货品名称'")
    private String goodsName;
    @Column(name = "goods_qty", columnDefinition = "int(11) comment '数量'")
    private Integer goodsQty;
    @Column(name = "money", columnDefinition = "double(10,5) comment '金额，等于 货品单价乘以数量'")
    private Double money;
    @Column(name = "order_id", columnDefinition = "varchar(255) comment '订单号'")
    private String orderId;
    @Column(name = "price", columnDefinition = "double(10,5) comment '货品单价(优惠前价格)'")
    private Double price;
}
