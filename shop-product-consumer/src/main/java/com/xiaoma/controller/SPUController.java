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

    /*获取spu列表*/
    @GetMapping("/spuList")
    public List<ProductInfo> productInfoList(Integer catalog3Id){
        return productClient.productInfoList(catalog3Id);
    }

    /*商品默认图片*/
    @PostMapping("/fileUpload")
    public String fileUpload(MultipartFile file) {
        return productClient.fileUpload(file);
    }

    /*保存spu*/
    @PostMapping("/saveSpuInfo")
    public void saveProductInfo(@RequestBody ProductInfo productInfo) {
        productClient.saveProductInfo(productInfo);
    }

    /*获取spu图片列表*/
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
