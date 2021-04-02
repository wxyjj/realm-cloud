package com.example.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2021/3/31 14:58
 * @Version 1.0
 */
@Entity
@Data
@Table(name = "ums_email_msg")
@org.hibernate.annotations.Table(appliesTo = "ums_email_msg", comment = "邮箱消息发送记录")
public class UmsEmailMsg implements Serializable {

    private static final long serialVersionUID = 7356064716338292800L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    @Column(name = "id", columnDefinition = "bigint(20) comment '主键id'")
    private Long id;
    @Column(name = "email_msg_id", columnDefinition = "varchar(32) comment 'email_msg_id'")
    private String emailMsgId;
    @Column(name = "receive_email", columnDefinition = "varchar(255) comment '接收邮箱'")
    private String receiveEmail;
    @Column(name = "code", columnDefinition = "integer(11) comment '邮箱code'")
    private Integer code;
    @Column(name = "retry", columnDefinition = "integer(11) comment '重试次数'")
    private Integer retry;
    @Column(name = "create_time", columnDefinition = "datetime(3) comment '创建时间'")
    private Date createTime;
}
