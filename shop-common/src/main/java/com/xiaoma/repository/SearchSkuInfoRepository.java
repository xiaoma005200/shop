package com.xiaoma.repository;

import com.xiaoma.pojo.SearchSkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 实体类类型,Id类型
 */
public interface SearchSkuInfoRepository extends ElasticsearchRepository<SearchSkuInfo,Long> {
}
