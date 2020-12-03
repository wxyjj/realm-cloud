package com.example.pdd.controller;

import com.example.pdd.support.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wxy
 * @Date 2020/12/2 14:23
 * @Version 1.0
 */
@RestController
@Api(tags = "pdd")
@RequestMapping("/pdd")
public class DemoController {

    @ApiOperation(value = "pdd接口")
    @GetMapping(value = "/pdd")
    public Result<Object> demo() {
        return Result.success("hello");
    }
}

