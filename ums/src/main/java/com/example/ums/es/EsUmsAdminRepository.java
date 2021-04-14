package com.example.ums.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author wxy
 * @Date 2021/4/14 16:18
 * @Version 1.0
 */
public interface EsUmsAdminRepository extends ElasticsearchRepository<EsUmsAdmin, Long> {
}
