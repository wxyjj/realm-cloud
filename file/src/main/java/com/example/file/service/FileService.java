package com.example.file.service;

import com.example.file.dto.DeleteFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author wxy
 * @Date 2021/3/3 11:38
 * @Version 1.0
 */
public interface FileService {
    /**
     * 文件上传
     */
    Object upload(MultipartFile multipartFile) throws Exception;

    /**
     * 删除文件
     */
    Object delete(DeleteFile deleteFile) throws Exception;
}
