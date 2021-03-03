package com.example.file.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2021/3/3 11:46
 * @Version 1.0
 */
@Data
public class Statement implements Serializable {
    private static final long serialVersionUID = -39435274253065656L;

    private String Effect;

    private String Principal;

    private String Action;

    private String Resource;
}
