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

    @GetMapping("/productInfoList")
    public List<ProductInfo> productInfoList(Integer catalog3Id){
        return productClient.productInfoList(catalog3Id);
    }

    @PostMapping("/fileUpload")
    public String fileUpload(MultipartFile file) {
        return productClient.fileUpload(file);
    }

    @PostMapping("/saveProductInfo")
    public void saveProductInfo(@RequestBody ProductInfo productInfo) {
        productClient.saveProductInfo(productInfo);
    }

    @GetMapping("/spuImageList")
    public List<ProductImage> spuImageList(Integer spuId) {
        return productClient.spuImageList(spuId);
    }

    @GetMapping("/spuSaleAttrList")
    public List<ProductSaleAttr> spuSaleAttrList(Integer spuId) {
        return productClient.spuSaleAttrList(spuId);
    }

    @GetMapping("/productSaleAttrsAndCheck")
    public List<ProductSaleAttr> productSaleAttrsAndCheck(Integer spuId,Integer skuId) {
        return productClient.productSaleAttrsAndCheck(spuId,skuId);
    }

    @GetMapping("/findSPUBySkuId")
    public ProductInfo findSPUBySkuId(Long skuId) {
        return productClient.findSPUBySkuId(skuId);
    }

}
