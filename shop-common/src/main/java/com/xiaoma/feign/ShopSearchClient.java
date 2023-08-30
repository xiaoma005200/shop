package com.xiaoma.feign;

import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("shop-search-provider")
public interface ShopSearchClient {

    @GetMapping("/searchByParams")
    PageResult<SearchSkuInfo> searchByParams(@SpringQueryMap ShopSearchQuery shopSearchQuery);
}
