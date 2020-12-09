package com.example.common.feign.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 拼多多订单数据解密
 *
 * @Author wxy
 * @Date 2020/12/7 16:10
 * @Version 1.0
 */
@Data
public class DecryptBatchDto implements Serializable {
    private static final long serialVersionUID = -4580551807695717620L;

    /**
     * id
     */
    private String id;
    /**
     * 解密tag，对于订单数据是订单号
     */
    private String dataTag;
    /**
     * 1、虚拟卡密;2、虚拟卡号;3、支付商品编码;4、支付单号;5、收件人;6、收件人手机号;7、收件人完整地址;8、收件人详细地址;9、快递单号;10、身份证号;11、身份证姓名
     */
    private Integer dataType;
    /**
     * 解密结果
     */
    private String decryptedData;
    /**
     * 解密结果
     */
    private String encryptedData;
}
