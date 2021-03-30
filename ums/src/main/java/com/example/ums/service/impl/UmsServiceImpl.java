package com.example.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.common.support.ApiException;
import com.example.common.user.UserDto;
import com.example.common.utils.CheckUtils;
import com.example.ums.entity.UmsAdmin;
import com.example.ums.entity.UmsRole;
import com.example.ums.repository.UmsAdminRepository;
import com.example.ums.service.UmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private UmsAdminRepository umsAdminRepository;

    /**
     * 根据用户名获取通用用户信息
     */
    @Override
    public UserDto loadUserByUsername(String username) {
        CheckUtils.checkNull(username, new ApiException(10000, "用户名不能为空"));

        UmsAdmin umsAdmin = umsAdminRepository.findWithUmsRoleByUsername(username);
        CheckUtils.checkNull(umsAdmin, new ApiException(10000, "未查询到用户"));

        List<String> roleStrList = new ArrayList<>();
        List<UmsRole> umsRoles = umsAdmin.getUmsRole();
        if (!CollUtil.isEmpty(umsRoles)) {
            roleStrList = umsRoles.stream().map(m -> m.getRoleId() + "_" + m.getName()).collect(Collectors.toList());
        }

        UserDto dto = new UserDto();
        dto.setId(umsAdmin.getAdminId());
        dto.setUsername(umsAdmin.getUsername());
        dto.setPassword(umsAdmin.getPassword());
        dto.setStatus(umsAdmin.getStatus());
        dto.setRoles(roleStrList);
        return dto;
    }
}
