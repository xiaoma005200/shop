package com.xiaoma.feign;

import com.xiaoma.pojo.SkuInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SKUClient {

    @PostMapping("/shop/product/saveSkuInfo")
    void saveSkuInfo(@RequestBody SkuInfo skuInfo);


}
