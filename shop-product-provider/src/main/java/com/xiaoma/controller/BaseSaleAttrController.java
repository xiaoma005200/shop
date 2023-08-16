package com.xiaoma.controller;

import com.xiaoma.pojo.BaseSaleAttr;
import com.xiaoma.service.BaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
public class BaseSaleAttrController {

    @Autowired
    BaseSaleAttrService baseSaleAttrService;

    @PostMapping("/baseSaleAttrList")
    public List<BaseSaleAttr> findAllSaleAttr(){
        return baseSaleAttrService.findAllSaleAttr();
    }
}
