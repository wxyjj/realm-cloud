package com.example.pdd.entiy;

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
@Table(name = "pdd_message_send")
@org.hibernate.annotations.Table(appliesTo = "pdd_message_send", comment = "拼多多消息推送记录   ")
public class PddMessageSend implements Serializable {
    private static final long serialVersionUID = -2049843445726185018L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "content", columnDefinition = "text comment '消息内容'")
    private String content;
    @Column(name = "create_time", columnDefinition = "datetime(3) comment '创建时间'")
    private Date createTime;
    @Column(name = "mall_id", columnDefinition = "varchar(255) comment '店铺id'")
    private String mallId;
    @Column(name = "type", columnDefinition = "varchar(255) comment '消息类型'")
    private String type;
}
