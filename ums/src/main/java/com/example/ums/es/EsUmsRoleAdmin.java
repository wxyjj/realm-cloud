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
@Document(indexName = "umsRole", replicas = 0)
public class EsUmsRoleAdmin implements Serializable {
    private static final long serialVersionUID = -8342750933878952400L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String roleId;
    private String name;
    private String description;
    private Date createTime;
    private Boolean status;
    private Integer sort;
}
