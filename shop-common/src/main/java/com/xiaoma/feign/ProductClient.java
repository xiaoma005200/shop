package com.xiaoma.feign;

import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.BaseCatalog1;
import com.xiaoma.pojo.BaseCatalog2;
import com.xiaoma.pojo.BaseCatalog3;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("shop-product-provider")
public interface ProductClient {

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
}
