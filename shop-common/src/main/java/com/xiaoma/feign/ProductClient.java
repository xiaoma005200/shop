package com.xiaoma.feign;

import com.xiaoma.pojo.BaseCatalog1;
import com.xiaoma.pojo.BaseCatalog2;
import com.xiaoma.pojo.BaseCatalog3;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("shop-product-provider")
public interface ProductClient {

    @PostMapping("/getCatalog1")
    List<BaseCatalog1> getCatalog1();

    @PostMapping("/getCatalog2")
    List<BaseCatalog2> getCatalog2(@RequestParam("catalog1Id") Integer catalog1Id);

    @PostMapping("/getCatalog3")
    List<BaseCatalog3> getCatalog3(@RequestParam("catalog2Id")Integer catalog2Id);
}
