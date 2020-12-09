package com.example.hpyf.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author wxy
 * @Date 2020/9/21 17:42
 * @Version 1.0
 */
@Data
@ApiModel(value = "OMS订单详情查询返回体")
public class OMSOrderDetailsResp implements Serializable {
    private static final long serialVersionUID = 7520615604700236767L;

    @ApiModelProperty(value = "订单号")
    private String orderid;
    @ApiModelProperty(value = "订单状态(1：待发货，2：已发货待签收，3：已签收 0：异常)")
    private Integer orderstatus;
    @ApiModelProperty(value = "订单创建时间")
    private Date credate;
    @ApiModelProperty(value = "ERP门店编码")
    private Integer placepointid;
    @ApiModelProperty(value = "门店名称")
    private String placepointname;
    @ApiModelProperty(value = "收货人名称")
    private String buyername;
    @ApiModelProperty(value = "性别 1-男，2-女，3未知")
    private Integer sex;
    @ApiModelProperty(value = "运费")
    private Double freight;
    @ApiModelProperty(value = "收货人省份")
    private String province;
    @ApiModelProperty(value = "收货人城市")
    private String city;
    @ApiModelProperty(value = "收货人所在区")
    private String district;
    @ApiModelProperty(value = "收货人地址")
    private String buyeraddress;
    @ApiModelProperty(value = "收货人电话")
    private String buyertelephone;
    @ApiModelProperty(value = "收货人地址高德坐标经度")
    private Double buyerLng;
    @ApiModelProperty(value = "收货人地址高德坐标纬度")
    private Double buyerLat;
    @ApiModelProperty(value = "用户下单备注")
    private String memo;
    @ApiModelProperty(value = "订单金额，客户实付金额")
    private Double money;
    @ApiModelProperty(value = "商家承担优惠金额")
    private Double discount;
    @ApiModelProperty(value = "订单当日流水号")
    private String order_index;
    @ApiModelProperty(value = "期望送达时间")
    private Date send_time;
    @ApiModelProperty(value = "是否需要发票 1-是 2-否")
    private Integer need_invoice;
    @ApiModelProperty(value = "发票抬头")
    private String invoice_title;
    @ApiModelProperty(value = "纳税人识别号")
    private String taxer_id;
    @ApiModelProperty(value = "配送方式 1自提，4配送")
    private Integer deliverystationno;
    @ApiModelProperty(value = "支付方式")
    private String orderpaytype;
    @ApiModelProperty(value = "处方标识 1-是 2-否")
    private Integer drugflag;
    @ApiModelProperty(value = "用药人姓名")
    private String usedrugname;
    @ApiModelProperty(value = "用药人性别 1-男，2-女，3未知")
    private Integer useDrugSex;
    @ApiModelProperty(value = "身份证号")
    private String identitynumber;
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
    @ApiModelProperty(value = "电话号码")
    private String phonenumber;
    @ApiModelProperty(value = "处方地址,当处方药有多张图片时,Url逗号隔开")
    private String picurl;
    @ApiModelProperty(value = "OMS订单明细返回体")
    private List<OMSOrderItemsResp> goodsinfo;
    @ApiModelProperty(value = "OMS订单处方信息返回体")
    private List<OMSOrderPrescriptionResp> prescriptionform;
}
