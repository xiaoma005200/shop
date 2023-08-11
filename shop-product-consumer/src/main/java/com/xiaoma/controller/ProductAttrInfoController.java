package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.service.ProductAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
public class ProductAttrInfoController {

    @Autowired
    ProductClient productClient;

    @GetMapping("/attrInfoList")
    public List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id){
        return productClient.getAttrByCatalog3Id(catalog3Id);
    }

    @PostMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        productClient.saveAttrInfo(baseAttrInfo);
        return "success";
    }
}
