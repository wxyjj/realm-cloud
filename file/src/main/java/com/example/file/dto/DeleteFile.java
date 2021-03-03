package com.example.file.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wxy
 * @Date 2021/3/3 11:49
 * @Version 1.0
 */
@Data
public class DeleteFile implements Serializable {
    private static final long serialVersionUID = -658169086500050475L;

    private String fileName;
}
