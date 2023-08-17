package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.feign.SPUClient;
import com.xiaoma.pojo.ProductImage;
import com.xiaoma.pojo.ProductInfo;
import com.xiaoma.pojo.ProductSaleAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/fileUpload")
    public String fileUpload(MultipartFile file) {
        return productClient.fileUpload(file);
    }

    @GetMapping("/productImageList")
    public List<ProductImage> productImageList(Integer spuId) {
        return productClient.productImageList(spuId);
    }

    @GetMapping("/productSaleAttrList")
    public List<ProductSaleAttr> productSaleAttrList(Integer spuId) {
        return productClient.productSaleAttrList(spuId);
    }

    @PostMapping("/saveSpuInfo")
    public void saveSpuInfo(@RequestBody ProductInfo productInfo) {
        productClient.saveSpuInfo(productInfo);
    }
}
