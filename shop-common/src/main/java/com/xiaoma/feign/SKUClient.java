package com.xiaoma.feign;

import com.xiaoma.pojo.Order;
import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.vo.OrderItem;
import com.xiaoma.vo.ResponseData;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SKUClient {

    @PostMapping("/shop/product/saveSkuInfo")
    void saveSkuInfo(@RequestBody SkuInfo skuInfo);

    @GetMapping("/shop/product/{skuInfoId}")
    SkuInfo findBySkuInfoId(@PathVariable("skuInfoId") Integer skuInfoId);

    @GetMapping("/shop/product/findAll")
    List<SkuInfo> findAll(@RequestParam("catalog3Id") Long catalog3Id);

    @GetMapping("/shop/product/findPriceBySkuId")
    BigDecimal findPriceBySkuId(@RequestParam("skuId") Long skuId);

    @PostMapping("/shop/product/getSkuStock")
    Map<Long, Boolean> getSkuStock(@RequestBody List<Long> skuIds);

    @PostMapping("/shop/product/orderLockStock")
    ResponseData<Order> orderLockStock(@RequestBody List<OrderItem> orderItemList);

}
