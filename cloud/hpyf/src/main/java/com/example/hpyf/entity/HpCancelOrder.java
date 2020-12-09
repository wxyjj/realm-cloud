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
@Table(name = "hp_cancel_order")
@org.hibernate.annotations.Table(appliesTo = "hp_cancel_order", comment = "和平取消订单")
public class HpCancelOrder implements Serializable {
    private static final long serialVersionUID = -217529603628125833L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "cancel_reason", columnDefinition = "varchar(255) comment '取消原因'")
    private String cancelReason;
    @Column(name = "mark", columnDefinition = "int(11) comment '成功标识，0成功，-1失败'")
    private Integer mark;
    @Column(name = "order_id", columnDefinition = "varchar(255) comment '订单号'")
    private String orderId;
    @Column(name = "pdd_data_id", columnDefinition = "varchar(32) comment '拼多多请求记录id'")
    private String pddDataId;
    @Column(name = "status", columnDefinition = "int(11) comment '订单状态 1超出配送范围，2商家已打烊，3商品已售完，4商品价格发生变化，5顾客要求取消，6顾客重复订单，7商家太忙无法及时备货 ，8顾客信息错误，10 发起申请，-1 其他'")
    private Integer status;
    @Column(name = "type", columnDefinition = "int(11) comment '操作类型，1待审核，2已取消 3已同意 4已拒绝 5已更改原单状态6已撤销申请'")
    private Integer type;
}
