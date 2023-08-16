package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.pojo.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
public class SPUController {

    @Autowired
    ProductClient productClient;

    @GetMapping("/spuList")
    public List<ProductInfo> findAll(Integer catalog3Id){
        return productClient.findAll(catalog3Id);
    }
}
