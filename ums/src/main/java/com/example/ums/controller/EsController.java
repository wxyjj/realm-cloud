package com.example.ums.controller;

import com.example.common.support.Result;
import com.example.ums.dto.req.ImportUmsEs;
import com.example.ums.service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2021/4/14 13:22
 * @Version 1.0
 */
@RestController
@Api(tags = "Es检索")
@RequestMapping("/es")
public class EsController {
    @Resource
    private EsService esService;

    @ApiOperation(value = "导入用户数据到Es中")
    @PostMapping(value = "/importUmsEs")
    public Result<Integer> importUmsEs(@RequestBody ImportUmsEs importUmsEs) {
        return Result.success(esService.importUmsEs(importUmsEs));
    }
}
