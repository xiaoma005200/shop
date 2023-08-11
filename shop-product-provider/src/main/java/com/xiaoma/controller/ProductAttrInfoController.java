package com.xiaoma.controller;

import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.service.ProductAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductAttrInfoController {

    @Autowired
    ProductAttrInfoService productAttrInfoService;

    @GetMapping("/attrInfoList")
    public List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id){
        return productAttrInfoService.getAttrByCatalog3Id(catalog3Id);
    }
}
