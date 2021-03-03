package com.example.file.controller;

import com.example.common.support.Result;
import com.example.file.dto.DeleteFile;
import com.example.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2021/2/16 15:25
 * @Version 1.0
 */
@RestController
@Api(tags = "文件服务")
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/upload")
    public Result<Object> upload(@RequestPart(value = "file") MultipartFile multipartFile) throws Exception {
        return Result.success(fileService.upload(multipartFile));
    }

    @ApiOperation(value = "删除文件", notes = "删除文件")
    @PostMapping(value = "/delete")
    public Result<Object> delete(@RequestBody DeleteFile deleteFile) throws Exception {
        return Result.success(fileService.delete(deleteFile));
    }
}
