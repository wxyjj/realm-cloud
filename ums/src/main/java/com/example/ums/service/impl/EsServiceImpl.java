package com.example.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.ums.entity.UmsAdmin;
import com.example.ums.es.EsUmsAdmin;
import com.example.ums.es.EsUmsAdminRepository;
import com.example.ums.service.EsService;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public Integer importEs(UmsAdmin umsAdmin) {
        EsUmsAdmin esUmsAdmin = new EsUmsAdmin();
        BeanUtil.copyProperties(umsAdmin, esUmsAdmin);
        esUmsAdminRepository.save(esUmsAdmin);
        return 1;
    }

}
