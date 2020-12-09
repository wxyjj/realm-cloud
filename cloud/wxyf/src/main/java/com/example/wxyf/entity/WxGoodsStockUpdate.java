package com.example.wxyf.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "wx_goods_stock_update")
@org.hibernate.annotations.Table(appliesTo = "wx_goods_stock_update", comment = "万鑫库存同步")
public class WxGoodsStockUpdate {

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "wx_store_code", columnDefinition = "varchar(255) comment '万鑫ERP门店编码'")
    private String wxStoreCode;
    @Column(name = "pdd_goods_id", columnDefinition = "varchar(255) comment '拼多多goodsId'")
    private String pddGoodsId;
    @Column(name = "pdd_sku_id", columnDefinition = "varchar(255) comment '拼多多skuId'")
    private String pddSkuId;
    @Column(name = "mark", columnDefinition = "int(11) comment '成功标识 1未成功 2成功'")
    private Integer mark;
    @Column(name = "quantity", columnDefinition = "varchar(255) comment '库存修改值。当全量更新库存时，quantity必须为大于等于0的正整数；当增量更新库存时，quantity为整数，可小于等于0。若增量更新时传入的库存为负数，则负数与实际库存之和不能小于0。比如当前实际库存为1，传入增量更新quantity=-1，库存改为0'")
    private String quantity;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;
    @Column(name = "update_type", columnDefinition = "int(11) comment '库存更新方式，可选。1为全量更新，2为增量更新。如果不填，默认为全量更新'")
    private Integer updateType;

}
