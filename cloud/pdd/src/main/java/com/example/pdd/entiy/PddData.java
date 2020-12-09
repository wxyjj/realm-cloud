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
@Table(name = "pdd_data")
@org.hibernate.annotations.Table(appliesTo = "pdd_data", comment = "拼多多请求记录数据")
public class PddData implements Serializable {
    private static final long serialVersionUID = -3781317456618296048L;

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id(UUID)'")
    private String id;
    @Column(name = "request_data", columnDefinition = "text comment '请求数据体'")
    private String requestData;
    @Column(name = "response_data", columnDefinition = "text comment '返回数据体'")
    private String responseData;
    @Column(name = "time", columnDefinition = "datetime(3) comment '时间'")
    private Date time;

    public PddData() {
    }

    public PddData(String id, String requestData, String responseData, Date time) {
        this.id = id;
        this.requestData = requestData;
        this.responseData = responseData;
        this.time = time;
    }
}
