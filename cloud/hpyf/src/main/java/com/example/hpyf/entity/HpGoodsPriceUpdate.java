package com.example.hpyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "hp_goods_price_update")
@org.hibernate.annotations.Table(appliesTo = "hp_goods_price_update", comment = "和平价格同步")
public class HpGoodsPriceUpdate implements Serializable {
    private static final long serialVersionUID = -4372636104369859843L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "cost_price", columnDefinition = "varchar(255) comment '货品成本价格'")
    private String costPrice;
    @Column(name = "mark", columnDefinition = "int(11) comment '成功标识 1未成功 2成功'")
    private Integer mark;
    @Column(name = "out_sku_id", columnDefinition = "varchar(255) comment '商家商品编码'")
    private String outSkuId;
    @Column(name = "pdd_data_id", columnDefinition = "varchar(255) comment '拼多多请求记录id'")
    private String pddDataId;
    @Column(name = "place_point_id", columnDefinition = "varchar(255) comment 'ERP门店ID'")
    private String placePointId;
    @Column(name = "price", columnDefinition = "varchar(255) comment '价格'")
    private String price;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;
}
