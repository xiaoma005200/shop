package com.xiaoma.controller;

import com.xiaoma.pojo.ProductInfo;
import com.xiaoma.service.SPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
public class SPUController {

    @Autowired
    SPUService spuService;

    @GetMapping("/spuList")
    public List<ProductInfo> findAll(Integer catalog3Id){
        return spuService.findAll(catalog3Id);
    }
}
