package com.example.wxyf.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/9/21 17:42
 * @Version 1.0
 */
@Data
@ApiModel(value = "订单处方信息返回体")
public class WxErpOrderPrescriptionResp implements Serializable {
    private static final long serialVersionUID = 7520615604700236767L;

    @ApiModelProperty(value = "医疗机构")
    private String medicalinstitution;
    @ApiModelProperty(value = "处方ID")
    private String prescriptionid;
    @ApiModelProperty(value = "处方日期")
    private String prescriptiondate;
    @ApiModelProperty(value = "科别")
    private String category;
    @ApiModelProperty(value = "用药人姓名")
    private String usedrugname;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "身份证号")
    private String identitynumber;
    @ApiModelProperty(value = "出生日期")
    private String birthday;
    @ApiModelProperty(value = "电话号码")
    private String phonenumber;
    @ApiModelProperty(value = "医生")
    private String doctor;
    @ApiModelProperty(value = "处方总金额")
    private String prescriptionTotalmoney;
    @ApiModelProperty(value = "处方单对应的货品信息")
    private String goods;
    @ApiModelProperty(value = "货品ID")
    private String goodsid;
    @ApiModelProperty(value = "货品数量")
    private String goodsqty;
    @ApiModelProperty(value = "货品单价")
    private String price;
}
