package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
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
    ProductClient productClient;

    /*根据catalog3Id获取商品对应的基本属性*/
    @GetMapping("/attrInfoList")
    public List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id){
        return productClient.getAttrByCatalog3Id(catalog3Id);
    }

    @PostMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        productClient.saveAttrInfo(baseAttrInfo);
        return "success";
    }

    /*根据attrId获取对应的属性值列表*/
    @PostMapping("/getAttrValueList")
    public List<BaseAttrValue> getAttrValueList(Integer attrId){
        return productClient.getAttrValueList(attrId);
    }
}
