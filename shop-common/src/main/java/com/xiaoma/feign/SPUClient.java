package com.xiaoma.feign;

import com.xiaoma.pojo.BaseSaleAttr;
import com.xiaoma.pojo.ProductImage;
import com.xiaoma.pojo.ProductInfo;
import com.xiaoma.pojo.ProductSaleAttr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SPUClient {

    @GetMapping("/shop/product/spuList")
    List<ProductInfo> findAll(@RequestParam("catalog3Id") Integer catalog3Id);

    @PostMapping("/shop/product/baseSaleAttrList")
    List<BaseSaleAttr> findAllSaleAttr();

    /**
     * @RequestParam("file")是vue中或者html中<input type="file" name="file"></input>的name的值
     * consumes指定方法处理何种请求
     * produces指定响应的数据类型
     * @param file
     * @return
     */
    @PostMapping(value = "/shop/product/fileUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart("file") MultipartFile file);

    @GetMapping("/shop/product/productImageList")
    List<ProductImage> productImageList(@RequestParam("spuId") Integer spuId);

    @GetMapping("/shop/product/productSaleAttrList")
    List<ProductSaleAttr> productSaleAttrList(@RequestParam("spuId") Integer spuId);

    @PostMapping("/shop/product/saveSpuInfo")
    void saveSpuInfo(@RequestBody ProductInfo productInfo);

}
