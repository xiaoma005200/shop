package com.xiaoma.controller;

import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.service.SKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/product")
public class SKUController {

    @Autowired
    SKUService skuService;

    @PostMapping("/saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        skuService.saveSkuInfo(skuInfo);
    }

    @GetMapping("/{skuInfoId}")
    public SkuInfo findBySkuInfoId(@PathVariable Integer skuInfoId) {
        return skuService.findBySkuInfoId(skuInfoId);
    }
}
