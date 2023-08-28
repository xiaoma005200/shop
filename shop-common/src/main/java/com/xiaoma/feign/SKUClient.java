package com.xiaoma.feign;

import com.xiaoma.pojo.SkuInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface SKUClient {

    @PostMapping("/shop/product/saveSkuInfo")
    void saveSkuInfo(@RequestBody SkuInfo skuInfo);

    @GetMapping("/shop/product/{skuInfoId}")
    SkuInfo findBySkuInfoId(@PathVariable("skuInfoId") Integer skuInfoId);

    @GetMapping("/shop/product/findAll")
    List<SkuInfo> findAll(@RequestParam("catalog3Id") Long catalog3Id);


}
