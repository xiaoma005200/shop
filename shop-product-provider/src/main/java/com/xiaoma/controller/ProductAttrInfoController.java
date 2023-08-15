package com.xiaoma.controller;

import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.BaseAttrValue;
import com.xiaoma.service.ProductAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
public class ProductAttrInfoController {

    @Autowired
    ProductAttrInfoService productAttrInfoService;

    @GetMapping("/attrInfoList")
    public List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id){
        return productAttrInfoService.getAttrByCatalog3Id(catalog3Id);
    }

    @PostMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        productAttrInfoService.saveAttrInfo(baseAttrInfo);
        return "success";
    }

    @PostMapping("/getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(Integer attrId){
        return productAttrInfoService.getAttrValueByAttrId(attrId);
    }
}
