package com.example.hpyf.controller;

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
@Api(tags = "hpyf")
@RequestMapping("/hpyf")
public class DemoController {

    @ApiOperation(value = "hpyf接口")
    @GetMapping(value = "/hpyf")
    public Result<Object> demo() {
        return Result.success("hello");
    }
}

