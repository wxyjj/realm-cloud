package com.example.wxyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "wx_goods_price_update")
@org.hibernate.annotations.Table(appliesTo = "wx_goods_price_update", comment = "万鑫价格同步")
public class WxGoodsPriceUpdate implements Serializable {
    private static final long serialVersionUID = -4372636104369859843L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "mark", columnDefinition = "int(11) comment '成功标识 1未成功 2成功'")
    private Integer mark;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;
    @Column(name = "pdd_goods_id", columnDefinition = "varchar(255) comment '拼多多goodsId'")
    private String pddGoodsId;
    @Column(name = "pdd_sku_id", columnDefinition = "varchar(255) comment '拼多多skuId'")
    private String pddSkuId;
    @Column(name = "group_price", columnDefinition = "varchar(255) comment '拼团购买价格（单位分）'")
    private String groupPrice;
    @Column(name = "single_price", columnDefinition = "varchar(255) comment '单独购买价格（单位分）'")
    private String singlePrice;
    @Column(name = "wx_store_code", columnDefinition = "varchar(255) comment '万鑫ERP门店编码'")
    private String wxStoreCode;
}
