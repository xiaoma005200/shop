package com.xiaoma.controller;

import com.xiaoma.pojo.BaseCatalog1;
import com.xiaoma.service.ProductCatagoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
public class ProductCatalogController {

    @Autowired
    ProductCatagoryService productCatagoryService;

    @GetMapping("/GetCatalog1")
    List<BaseCatalog1> GetCatalog1(){
        return productCatagoryService.findAll();
    }
}
