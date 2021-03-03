package com.example.file.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.json.JSONUtil;
import com.example.common.support.ApiException;
import com.example.common.utils.CheckUtils;
import com.example.file.dto.BucketPolicyConfigDto;
import com.example.file.dto.DeleteFile;
import com.example.file.dto.Statement;
import com.example.file.properties.MinIoProperties;
import com.example.file.service.FileService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Collections;

/**
 * @Author wxy
 * @Date 2021/3/3 11:38
 * @Version 1.0
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private HttpServletRequest request;
    @Resource
    private MinIoProperties minIoProperties;

    private String createBucketPolicyConfigDto(String bucketName) {
        Statement statement = new Statement();
        statement.setEffect("Allow");
        statement.setPrincipal("*");
        statement.setAction("s3:GetObject");
        statement.setResource("arn:aws:s3:::" + bucketName + "/*.**");

        BucketPolicyConfigDto bucketPolicyConfigDto = new BucketPolicyConfigDto();
        bucketPolicyConfigDto.setVersion("2012-10-17");
        bucketPolicyConfigDto.setStatement(Collections.singletonList(statement));

        return JSONUtil.toJsonStr(bucketPolicyConfigDto);
    }

    /**
     * 文件上传
     */
    @Override
    public Object upload(MultipartFile multipartFile) throws Exception {
        log.info("请求ip:" + request.getRemoteAddr());
        CheckUtils.checkNull(multipartFile, new ApiException(10000, "未检测到上传文件"));
        if (multipartFile.getSize() >= 1024 * 1024 * 100L) {
            throw new ApiException(10000, "文件上传大小不得超过100M");
        }
        //文件流
        InputStream inputStream = multipartFile.getInputStream();
        //文件大小
        long size = multipartFile.getSize();
        //文件contentType
        String contentType = multipartFile.getContentType();
        //文件md5
        String md5 = new Digester(DigestAlgorithm.MD5).digestHex(multipartFile.getInputStream());
        //源文件名
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        //取原文件名后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //定义新文件名
        String fileName = md5 + "." + suffix;
        //打开文件监听
        MinioClient minioClient = MinioClient.builder().endpoint(minIoProperties.getEndpoint()).credentials(minIoProperties.getAccessKey(), minIoProperties.getSecretKey()).build();
        //桶
        String bucket = minIoProperties.getBucketName();
        //若桶不存在，则创建桶
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!isExist) {
            //创建存储桶
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            //设置桶策略
            String policyJson = createBucketPolicyConfigDto(bucket);
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucket).config(policyJson).build());
        }
        //上传
        minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(fileName).stream(inputStream, size, -1).contentType(contentType).build());
        String url = minIoProperties.getEndpoint() + "/" + bucket + "/" + fileName;
        log.info("文件上传成功!地址:" + url);
        return url;
    }

    /**
     * 删除文件
     */
    @Override
    public Object delete(DeleteFile deleteFile) throws Exception {
        CheckUtils.checkNull(deleteFile, new ApiException(10000, "请求对象不能为空"));
        String fileName = CheckUtils.checkNullReturn(deleteFile.getFileName(), new ApiException(10000, "文件名传入为空"));
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minIoProperties.getEndpoint())
                .credentials(minIoProperties.getAccessKey(), minIoProperties.getSecretKey())
                .build();
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(minIoProperties.getBucketName()).object(fileName).build());
        return null;
    }
}
