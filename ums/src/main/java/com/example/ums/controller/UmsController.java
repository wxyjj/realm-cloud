package com.example.ums.controller;

import com.example.common.support.Result;
import com.example.common.user.UserDto;
import com.example.ums.dto.req.SendEmailReq;
import com.example.ums.service.UmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2021/3/16 16:02
 * @Version 1.0
 */
@RestController
@Api(tags = "后台用户管理")
@RequestMapping("/ums")
public class UmsController {
    @Resource
    private UmsService umsService;

    @ApiOperation(value = "根据用户名获取通用用户信息")
    @GetMapping(value = "/loadUserByUsername")
    public Result<UserDto> loadUserByUsername(@RequestParam(value = "username") String username) {
        return Result.success(umsService.loadUserByUsername(username));
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/sendEmail")
    public Result<Object> sendEmail(@RequestBody SendEmailReq req) {
        return Result.success(umsService.sendEmail(req));
    }
}
