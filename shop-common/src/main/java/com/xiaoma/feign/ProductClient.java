package com.xiaoma.feign;

import com.xiaoma.pojo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("shop-product-provider")
public interface ProductClient extends SPUClient, SKUClient {

    @PostMapping("/shop/product/getCatalog1")
    List<BaseCatalog1> getCatalog1();

    @PostMapping("/shop/product/getCatalog2")
    List<BaseCatalog2> getCatalog2(@RequestParam("catalog1Id") Integer catalog1Id);

    @PostMapping("/shop/product/getCatalog3")
    List<BaseCatalog3> getCatalog3(@RequestParam("catalog2Id") Integer catalog2Id);

    @GetMapping("/shop/product/attrInfoList")
    List<BaseAttrInfo> getAttrByCatalog3Id(@RequestParam("catalog3Id") Integer catalog3Id);

    @PostMapping("/shop/product/saveAttrInfo")
    String saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo);

    @PostMapping("/shop/product/getAttrValueList")
    List<BaseAttrValue> getAttrValueList(@RequestParam("attrId") Integer attrId);

    @PostMapping("/shop/product/getAttrByValueIds")
    List<BaseAttrInfo> getAttrByValueIds(@RequestBody List<Integer> valueIds);

    @PostMapping("/shop/product/getSkuIdByValueIds")
    @ResponseBody
    String getSkuIdByValueIds(@RequestParam("saleAttrValueIds") String saleAttrValueIds);
}
