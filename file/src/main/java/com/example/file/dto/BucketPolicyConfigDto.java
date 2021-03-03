package com.example.file.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wxy
 * @Date 2021/3/3 11:45
 * @Version 1.0
 */
@Data
public class BucketPolicyConfigDto implements Serializable {
    private static final long serialVersionUID = 2797924918302239749L;

    private String Version;

    private List<Statement> Statement;
}
