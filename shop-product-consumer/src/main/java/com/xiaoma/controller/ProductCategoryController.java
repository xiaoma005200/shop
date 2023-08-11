package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.pojo.BaseCatalog1;
import com.xiaoma.pojo.BaseCatalog2;
import com.xiaoma.pojo.BaseCatalog3;
import com.xiaoma.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
//@CrossOrigin //允许跨域
public class ProductCategoryController {

    @Autowired
    ProductClient productClient;

    @PostMapping("/getCatalog1")
    public List<BaseCatalog1> getCatalog1(){
        return productClient.getCatalog1();
    }

    @PostMapping("/getCatalog2")
    public List<BaseCatalog2> getCatalog2(Integer catalog1Id){
        return productClient.getCatalog2(catalog1Id);
    }

    @PostMapping("/getCatalog3")
    public List<BaseCatalog3> getCatalog3(Integer catalog2Id){
        return productClient.getCatalog3(catalog2Id);
    }
}
