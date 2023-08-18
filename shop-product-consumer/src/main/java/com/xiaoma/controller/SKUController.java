package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.pojo.SkuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/product")
public class SKUController {

    @Autowired
    ProductClient productClient;

    @PostMapping("/saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        productClient.saveSkuInfo(skuInfo);
    }
}
