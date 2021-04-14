package com.example.ums.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2021/4/14 16:18
 * @Version 1.0
 */
@Data
@Document(indexName = "ums", replicas = 0)
public class EsUmsAdmin implements Serializable {
    private static final long serialVersionUID = -8342750933878952400L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String adminId;
    @Field(type = FieldType.Keyword)
    private String username;
    private String password;
    private String icon;
    private String email;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String nickName;
    private String note;
    private Date createTime;
    private Date loginTime;
    private Boolean status;
}
