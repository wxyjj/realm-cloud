package com.example.pdd.entiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2020/12/1 15:41
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "pdd_decrypt_batch")
@org.hibernate.annotations.Table(appliesTo = "pdd_decrypt_batch", comment = "拼多多订单数据解密")
public class PddDecryptBatch implements Serializable {
    private static final long serialVersionUID = -946675432545603914L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "data_tag", columnDefinition = "varchar(255) comment '解密tag，对于订单数据是订单号'")
    private String dataTag;
    @Column(name = "data_type", columnDefinition = "int(11) comment '1、虚拟卡密;2、虚拟卡号;3、支付商品编码;4、支付单号;5、收件人;6、收件人手机号;7、收件人完整地址;8、收件人详细地址;9、快递单号;10、身份证号;11、身份证姓名'")
    private Integer dataType;
    @Column(name = "decrypted_data", columnDefinition = "text comment '解密结果'")
    private String decryptedData;
    @Column(name = "encrypted_data", columnDefinition = "text comment '加密数据'")
    private String encryptedData;
}
