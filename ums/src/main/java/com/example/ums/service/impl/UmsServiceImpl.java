package com.example.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.common.support.ApiException;
import com.example.common.user.UserDto;
import com.example.common.utils.CheckUtils;
import com.example.ums.entity.UmsAdmin;
import com.example.ums.entity.UmsAdminRoleRel;
import com.example.ums.entity.UmsRole;
import com.example.ums.jpa.UmsAdminRepository;
import com.example.ums.jpa.UmsAdminRoleRelRepository;
import com.example.ums.jpa.UmsRoleRepository;
import com.example.ums.service.UmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private UmsRoleRepository umsRoleRepository;
    @Resource
    private UmsAdminRoleRelRepository umsAdminRoleRelRepository;

    /**
     * 根据用户名获取通用用户信息
     */
    @Override
    public UserDto loadUserByUsername(String username) {
        CheckUtils.checkNull(username, new ApiException(10000, "用户名不能为空"));
        UmsAdmin admin = umsAdminRepository.findFirstByUsername(username);
        CheckUtils.checkNull(admin, new ApiException(10000, "未查询到用户"));
        List<UmsAdminRoleRel> umsAdminRoleRelList = umsAdminRoleRelRepository.findAllByAdminId(admin.getId());
        if (CollUtil.isEmpty(umsAdminRoleRelList)) {
            throw new ApiException(10000, "用户未关联权限");
        }
        List<String> roleIds = umsAdminRoleRelList.stream().map(UmsAdminRoleRel::getRoleId).collect(Collectors.toList());
        List<UmsRole> umsRoleList = umsRoleRepository.findAllByIdIn(roleIds);
        if (CollUtil.isEmpty(umsRoleList)) {
            throw new ApiException(10000, "未查询到权限列表");
        }
        List<String> roleStrList = umsRoleList.stream().map(m -> m.getId() + "_" + m.getName()).collect(Collectors.toList());

        UserDto dto = new UserDto();
        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setPassword(admin.getPassword());
        dto.setStatus(admin.getStatus());
        dto.setRoles(roleStrList);
        return dto;
    }
}
