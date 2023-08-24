package com.xiaoma.controller;

import com.xiaoma.feign.ProductClient;
import com.xiaoma.pojo.ProductSaleAttr;
import com.xiaoma.pojo.SkuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop/product")
public class ProductDetailController {
    @Autowired
    ProductClient productClient;

    @GetMapping("/{skuInfoId}.html")
    public String detail(@PathVariable Integer skuInfoId, Model model) {
        // 1.查询出当前sku信息
        SkuInfo skuInfo = productClient.findBySkuInfoId(skuInfoId);
        model.addAttribute("skuInfo", skuInfo);

        // 2.根据spuId查询出对应的销售属性和销售属性值
        //List<ProductSaleAttr> productSaleAttrList = productClient.spuSaleAttrList(skuInfo.getProductId().intValue());
        //model.addAttribute("productSaleAttrList", productSaleAttrList);
        List<ProductSaleAttr> productSaleAttrList = productClient.productSaleAttrsAndCheck(skuInfo.getProductId().intValue(), skuInfoId);
        model.addAttribute("productSaleAttrList", productSaleAttrList);

        return "productDetail";
    }


}

