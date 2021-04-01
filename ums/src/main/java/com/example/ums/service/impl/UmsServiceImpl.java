package com.example.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.support.ApiException;
import com.example.common.user.UserDto;
import com.example.common.utils.CheckUtils;
import com.example.ums.dto.EmailDto;
import com.example.ums.dto.req.SendEmailReq;
import com.example.ums.dto.resp.RoleResp1;
import com.example.ums.entity.UmsAdmin;
import com.example.ums.mapper.UmsAdminMapper;
import com.example.ums.mapper.UmsAdminRoleRelMapper;
import com.example.ums.mq.EmailSender;
import com.example.ums.service.UmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wxy
 * @Date 2021/3/16 16:03
 * @Version 1.0
 */
@Service
public class UmsServiceImpl implements UmsService {
    @Resource
    private EmailSender emailSender;
    @Resource
    private HttpServletRequest request;

    @Resource
    private UmsAdminMapper umsAdminMapper;
    @Resource
    private UmsAdminRoleRelMapper umsAdminRoleRelMapper;

    /**
     * 根据用户名获取通用用户信息
     */
    @Override
    public UserDto loadUserByUsername(String username) {
        CheckUtils.checkNull(username, new ApiException(10000, "用户名不能为空"));
        UmsAdmin umsAdmin = umsAdminMapper.findUmsAdminByUserName(username);
        CheckUtils.checkNull(umsAdmin, new ApiException(10000, "未查询到用户"));
        List<String> roleStrList = new ArrayList<>();
        List<RoleResp1> roleResp1List = umsAdminRoleRelMapper.findRoleResp1ByAdminId(umsAdmin.getAdminId());
        if (!CollUtil.isEmpty(roleResp1List)) {
            roleStrList = roleResp1List.stream().map(m -> m.getRoleId() + "_" + m.getName()).collect(Collectors.toList());
        }
        UserDto dto = new UserDto();
        dto.setId(umsAdmin.getAdminId());
        dto.setUsername(umsAdmin.getUsername());
        dto.setPassword(umsAdmin.getPassword());
        dto.setStatus(umsAdmin.getStatus());
        dto.setRoles(roleStrList);
        return dto;
    }

    /**
     * 发送邮件
     */
    @Override
    public Object sendEmail(SendEmailReq req) {
        CheckUtils.checkNull(req, new ApiException(10000, "请求对象不能为空"));
        String userName = req.getUserName();
        String email = req.getEmail();
        if (!StrUtil.isEmpty(userName)) {
            email = umsAdminMapper.findEmailByUserName(userName);
        }
        if (StrUtil.isEmpty(email)) {
            throw new ApiException(10000, "未找到邮箱!");
        }
        InputStream inputStream = this.getClass().getResourceAsStream("/email.txt");
        String template = IoUtil.read(inputStream, CharsetUtil.CHARSET_UTF_8);
        Integer code = RandomUtil.randomInt(100000, 999999);
        String device = request.getHeader("User-Agent");
        String content = String.format(template, device, code);

        EmailDto emailDto = new EmailDto();
        emailDto.setMsgId(IdUtil.simpleUUID());
        emailDto.setCode(code);
        emailDto.setUserEmail(email);
        emailDto.setSubject("Please verify your device");
        emailDto.setContent(content);
        emailSender.sendMessage(emailDto, Duration.ofMinutes(1));
        return null;
    }
}
