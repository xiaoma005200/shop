package com.xiaoma.controller;

import com.xiaoma.pojo.ProductImage;
import com.xiaoma.pojo.ProductInfo;
import com.xiaoma.pojo.ProductSaleAttr;
import com.xiaoma.service.SPUService;
import com.xiaoma.util.FastDFSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/shop/product")
public class SPUController {

    @Autowired
    SPUService spuService;

    @Autowired
    FastDFSUtils fastDFSUtils;

    @GetMapping("/productInfoList")
    public List<ProductInfo> productInfoList(Integer catalog3Id){
        return spuService.findAll(catalog3Id);
    }

    /**
     *
     * @param file 跟 <input type=file name=xxx/>name的属性值对应
     * @return
     */
    @PostMapping("/fileUpload")
    public String fileUpload(MultipartFile file) {
        return fastDFSUtils.uploadFile(file);
    }

    @PostMapping("/saveProductInfo")
    public void saveProductInfo(@RequestBody ProductInfo productInfo) {
        spuService.saveProductInfo(productInfo);
    }

    @GetMapping("/spuImageList")
    public List<ProductImage> spuImageList(Integer spuId) {
        return spuService.findImagesByPid(spuId);
    }

    @GetMapping("/spuSaleAttrList")
    public List<ProductSaleAttr> spuSaleAttrList(Integer spuId) {
        return spuService.findSaleAttrByPid(spuId);
    }

    @GetMapping("/productSaleAttrsAndCheck")
    public List<ProductSaleAttr> productSaleAttrsAndCheck(Integer spuId,Integer skuId) {
        return spuService.findSPUSaleAttrAndCheck(spuId, skuId);
    }

    @GetMapping("/findSPUBySkuId")
    public ProductInfo findSPUBySkuId(Long skuId) {
        return spuService.findSPUBySkuId(skuId);
    }

}
