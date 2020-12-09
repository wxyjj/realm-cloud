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
 * @Date 2020/9/21 17:42
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "wx_order_details")
@org.hibernate.annotations.Table(appliesTo = "wx_order_details", comment = "万鑫订单详情")
public class WxOrderDetails implements Serializable {
    private static final long serialVersionUID = -6831063076962277406L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "birthday", columnDefinition = "date comment '用药人出生日期'")
    private Date birthday;
    @Column(name = "buyer_address", columnDefinition = "varchar(255) comment '收货人地址'")
    private String buyerAddress;
    @Column(name = "buyer_lat", columnDefinition = "double(10,5) comment '收货人地址高德坐标纬度'")
    private Double buyerLat;
    @Column(name = "buyer_lng", columnDefinition = "double(10,5) comment '收货人地址高德坐标经度'")
    private Double buyerLng;
    @Column(name = "buyer_name", columnDefinition = "varchar(255) comment '收货人名称'")
    private String buyerName;
    @Column(name = "buyer_telephone", columnDefinition = "varchar(255) comment '收货人电话'")
    private String buyerTelephone;
    @Column(name = "city", columnDefinition = "varchar(255) comment '收货人城市'")
    private String city;
    @Column(name = "create_date", columnDefinition = "datetime(3) comment '订单创建时间'")
    private Date createDate;
    @Column(name = "discount", columnDefinition = "double(10,5) comment '商家承担优惠金额'")
    private Double discount;
    @Column(name = "district", columnDefinition = "varchar(255) comment '收货人所在区'")
    private String district;
    @Column(name = "drug_flag", columnDefinition = "int(11) comment '处方标识 1-是 2-否'")
    private Integer drugFlag;
    @Column(name = "freight", columnDefinition = "double(10,5) comment '运费'")
    private Double freight;
    @Column(name = "identity_number", columnDefinition = "varchar(255) comment '用药人身份证号'")
    private String identityNumber;
    @Column(name = "invoice_title", columnDefinition = "varchar(255) comment '发票抬头'")
    private String invoiceTitle;
    @Column(name = "memo", columnDefinition = "varchar(255) comment '用户下单备注'")
    private String memo;
    @Column(name = "money", columnDefinition = "double(10,5) comment '订单金额，客户实付金额'")
    private Double money;
    @Column(name = "need_invoice", columnDefinition = "int(11) comment '是否需要发票 1-是 2-否'")
    private Integer needInvoice;
    @Column(name = "order_id", columnDefinition = "varchar(255) comment '订单号'")
    private String orderId;
    @Column(name = "order_index", columnDefinition = "varchar(255) comment '订单当日流水号'")
    private String orderIndex;
    @Column(name = "order_status", columnDefinition = "int(11) comment '订单状态(1：待发货，2：已发货待签收，3：已签收 0：异常)'")
    private Integer orderStatus;
    @Column(name = "pdd_data_id", columnDefinition = "varchar(32) comment '拼多多请求记录id'")
    private String pddDataId;
    @Column(name = "phone_number", columnDefinition = "varchar(255) comment '用药人电话号码'")
    private String phoneNumber;
    @Column(name = "pic_url", columnDefinition = "text comment '处方地址,当处方药有多张图片时,Url逗号隔开'")
    private String picUrl;
    @Column(name = "place_point_id", columnDefinition = "int(11) comment 'ERP门店编码'")
    private Integer placePointId;
    @Column(name = "place_point_name", columnDefinition = "varchar(255) comment '门店名称'")
    private String placePointName;
    @Column(name = "province", columnDefinition = "varchar(255) comment '收货人省份'")
    private String province;
    @Column(name = "send_time", columnDefinition = "datetime(3) comment '期望送达时间'")
    private Date sendTime;
    @Column(name = "sex", columnDefinition = "int(11) comment '性别 1-男，2-女，3未知'")
    private Integer sex;
    @Column(name = "taxer_id", columnDefinition = "varchar(255) comment '纳税人识别号'")
    private String taxerId;
    @Column(name = "use_drug_name", columnDefinition = "varchar(255) comment '用药人姓名'")
    private String useDrugName;
    @Column(name = "use_sex", columnDefinition = "int(11) comment '用药人性别 1-男，2-女，3未知'")
    private Integer useSex;
    @Column(name = "refund_status", columnDefinition = "int(11) comment '订单售后状态，1：无售后或售后关闭，2：售后处理中，3：退款中，4：退款成功，0：异常'")
    private Integer refundStatus;
    @Column(name = "pay_type", columnDefinition = "varchar(255) comment '支付方式，枚举值：QQ,WEIXIN,ALIPAY,LIANLIANPAY'")
    private String payType;
}
