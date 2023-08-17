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

    @GetMapping("/spuList")
    public List<ProductInfo> findAll(Integer catalog3Id){
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

    @GetMapping("/productImageList")
    public List<ProductImage> productImageList(Integer spuId) {
        return spuService.findImagesByPid(spuId);
    }

    @GetMapping("/productSaleAttrList")
    public List<ProductSaleAttr> productSaleAttrList(Integer spuId) {
        return spuService.findSaleAttrByPid(spuId);
    }

    @PostMapping("/saveSpuInfo")
    public void saveSpuInfo(@RequestBody ProductInfo productInfo) {
        spuService.saveSpuInfo(productInfo);
    }

}
