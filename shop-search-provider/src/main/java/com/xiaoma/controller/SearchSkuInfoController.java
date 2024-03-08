package com.xiaoma.controller;

import com.xiaoma.pojo.SearchSkuInfo;
import com.xiaoma.service.SearchSkuInfoService;
import com.xiaoma.vo.PageResult;
import com.xiaoma.vo.ShopSearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/product")
public class SearchSkuInfoController {

    @Autowired
    SearchSkuInfoService searchSkuInfoService;

    @GetMapping("/searchByParams")
    public PageResult<SearchSkuInfo> searchByParams(ShopSearchQuery shopSearchQuery){
        return searchSkuInfoService.searchByParams(shopSearchQuery);
    }

}
