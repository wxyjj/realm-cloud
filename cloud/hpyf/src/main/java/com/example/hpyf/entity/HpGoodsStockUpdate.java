package com.example.hpyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "hp_goods_stock_update")
@org.hibernate.annotations.Table(appliesTo = "hp_goods_stock_update", comment = "和平库存同步")
public class HpGoodsStockUpdate {

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "erp_shop_id", columnDefinition = "varchar(255) comment 'ERP门店ID'")
    private String erpShopId;
    @Column(name = "out_sku_id", columnDefinition = "varchar(255) comment '商家商品编码'")
    private String outSkuId;
    @Column(name = "mark", columnDefinition = "int(11) comment '成功标识 1未成功 2成功'")
    private Integer mark;
    @Column(name = "goods_qty", columnDefinition = "varchar(255) comment '货品可销库存数量'")
    private String goodsQty;
    @Column(name = "pdd_data_id", columnDefinition = "varchar(255) comment '拼多多请求记录id'")
    private String pddDataId;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;
    @Column(name = "number", columnDefinition = "int(11) comment '更新前库存数量'")
    private String number;

}
