package com.example.ums.service.impl;

import com.example.ums.dto.req.ImportUmsEs;
import com.example.ums.entity.UmsAdmin;
import com.example.ums.entity.UmsRole;
import com.example.ums.es.EsUmsAdmin;
import com.example.ums.es.EsUmsAdminRepository;
import com.example.ums.es.EsUmsRoleAdmin;
import com.example.ums.service.EsService;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wxy
 * @Date 2021/4/14 13:23
 * @Version 1.0
 */
@Service
public class EsServiceImpl implements EsService {
    @Resource
    private EsUmsAdminRepository esUmsAdminRepository;
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Integer importUmsEs(ImportUmsEs importUmsEs) {
        UmsAdmin umsAdmin = importUmsEs.getUmsAdmin();
        List<UmsRole> umsRoleList = importUmsEs.getUmsRoleList();

        EsUmsAdmin esUmsAdmin = new EsUmsAdmin();
        esUmsAdmin.setId(umsAdmin.getId());
        esUmsAdmin.setAdminId(umsAdmin.getAdminId());
        esUmsAdmin.setUsername(umsAdmin.getUsername());
        esUmsAdmin.setPassword(umsAdmin.getPassword());
        esUmsAdmin.setIcon(umsAdmin.getIcon());
        esUmsAdmin.setEmail(umsAdmin.getEmail());
        esUmsAdmin.setNickName(umsAdmin.getNickName());
        esUmsAdmin.setNote(umsAdmin.getNote());
        esUmsAdmin.setCreateTime(umsAdmin.getCreateTime());
        esUmsAdmin.setLoginTime(umsAdmin.getLoginTime());
        esUmsAdmin.setStatus(umsAdmin.getStatus());

        esUmsAdmin.setEsUmsRoleAdminList(
                umsRoleList.stream().map(m -> {
                    EsUmsRoleAdmin esUmsRoleAdmin = new EsUmsRoleAdmin();
                    esUmsRoleAdmin.setId(m.getId());
                    esUmsRoleAdmin.setRoleId(m.getRoleId());
                    esUmsRoleAdmin.setName(m.getName());
                    esUmsRoleAdmin.setDescription(m.getDescription());
                    esUmsRoleAdmin.setCreateTime(m.getCreateTime());
                    esUmsRoleAdmin.setStatus(m.getStatus());
                    esUmsRoleAdmin.setSort(m.getSort());
                    return esUmsRoleAdmin;
                }).collect(Collectors.toList())
        );
        esUmsAdminRepository.save(esUmsAdmin);
        return 1;
    }

}
