package com.example.ums.controller;

import com.example.common.support.Result;
import com.example.ums.entity.UmsAdmin;
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

    @ApiOperation(value = "导入数据到Es中")
    @PostMapping(value = "/importEs")
    public Result<Integer> importEs(@RequestBody UmsAdmin umsAdmin) {
        return Result.success(esService.importEs(umsAdmin));
    }
}
