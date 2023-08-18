package com.xiaoma.controller;

import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.service.SKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/product")
public class SKUController {

    @Autowired
    SKUService skuService;

    @PostMapping("/saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        skuService.saveSkuInfo(skuInfo);
    }
}
