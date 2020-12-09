package com.example.wxyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "wx_goods")
@org.hibernate.annotations.Table(appliesTo = "wx_goods", comment = "万鑫商品对码")
public class WxGoods {

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "pdd_goods_id", columnDefinition = "varchar(255) comment '拼多多goodsId'")
    private String pddGoodsId;
    @Column(name = "pdd_sku_id", columnDefinition = "varchar(255) comment '拼多多skuId'")
    private String pddSkuId;
    @Column(name = "wx_sku_id", columnDefinition = "varchar(255) comment '万鑫skuId'")
    private String outSkuId;
    @Column(name = "wx_goods_id", columnDefinition = "varchar(255) comment '万鑫goodsId'")
    private String outGoodsId;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;
    @Column(name = "goods_name", columnDefinition = "varchar(255) comment '拼多多商品名'")
    private String goodsName;
    @Column(name = "specifications", columnDefinition = "varchar(255) comment '拼多多规格名称'")
    private String specifications;
    @Column(name = "mall_id", columnDefinition = "varchar(255) comment '拼多多店铺编码'")
    private String mallId;
    @Column(name = "receiving_time", columnDefinition = "bigint(20) comment '接收时间时间戳'")
    private Long receivingTime;
}
