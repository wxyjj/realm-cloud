package com.example.wxyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/10/28 10:45
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "wx_order_message")
@org.hibernate.annotations.Table(appliesTo = "wx_order_message", comment = "订单消息推送万鑫的ERP")
public class WxOrderMessage implements Serializable {
    private static final long serialVersionUID = -7775103655837850595L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "order_id", columnDefinition = "varchar(255) comment '订单号'")
    private String orderId;
    @Column(name = "state", columnDefinition = "tinyint(1) comment '推送成功标识 0-否 1-是'")
    private Boolean state;
}
