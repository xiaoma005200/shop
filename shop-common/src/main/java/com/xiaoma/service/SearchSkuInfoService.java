package com.xiaoma.service;

import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchQuery;

import java.util.List;

/**
 * 查询skuInfo
 */
public interface SearchSkuInfoService {

    /**
     *
     * @param shopSearchQuery 封装页面的查询参数
     * @return 从ES搜索到的相关结果
     */
    PageResult<SearchSkuInfo> searchByParams(ShopSearchQuery shopSearchQuery);
}
