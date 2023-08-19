package com.xiaoma.feign;

import com.xiaoma.pojo.SkuInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SKUClient {

    @PostMapping("/shop/product/saveSkuInfo")
    void saveSkuInfo(@RequestBody SkuInfo skuInfo);

    @GetMapping("/shop/product/{skuInfoId}")
    SkuInfo findBySkuInfoId(@PathVariable("skuInfoId") Integer skuInfoId);


}
