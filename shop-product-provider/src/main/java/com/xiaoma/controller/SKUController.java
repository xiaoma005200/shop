package com.xiaoma.controller;

import com.xiaoma.constant.OrderStatusEnum;
import com.xiaoma.exception.LockStockException;
import com.xiaoma.pojo.Order;
import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.service.SKUService;
import com.xiaoma.util.ResultUtils;
import com.xiaoma.vo.OrderItem;
import com.xiaoma.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop/product")
public class SKUController {

    @Autowired
    SKUService skuService;

    @PostMapping("/saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        skuService.saveSkuInfo(skuInfo);
        //System.out.println(skuInfo);
    }

    @GetMapping("/{skuInfoId}")
    public SkuInfo findBySkuInfoId(@PathVariable Integer skuInfoId) {
        return skuService.findBySkuInfoId(skuInfoId);
    }

    @GetMapping("/findSkuSaleAttrValuesBySpuId")
    public Map<String, Long> findSkuSaleAttrValuesBySpuId(Long spuId) {
        return skuService.findSkuSaleAttrValuesBySpuId(spuId);
    }

    @GetMapping("/findAll")
    public List<SkuInfo> findAll(Long catalog3Id){
        return skuService.findAll(catalog3Id);
    }

    @PostMapping("/getSkuIdByValueIds")
    @ResponseBody
    public String getSkuIdByValueIds(String saleAttrValueIds) {
        return skuService.selectSkuIdByValueIds(saleAttrValueIds);
    }

    @GetMapping("/findPriceBySkuId")
    public BigDecimal findPriceBySkuId(Long skuId) {
        return skuService.findPriceBySkuId(skuId);
    }

    @PostMapping("/getSkuStock")
    public Map<Long, Boolean> getSkuStock(@RequestBody List<Long> skuIds){
        return skuService.getSkuStock(skuIds);
    }

    @PostMapping("/orderLockStock")
    public ResponseData<Order> orderLockStock(@RequestBody List<OrderItem> orderItemList) {
        try {
            skuService.orderLockStock(orderItemList);
        } catch (LockStockException e) {
            e.printStackTrace();
            return ResultUtils.result(OrderStatusEnum.LOCKED_STOCK_FAIL, null);
        }
        return ResultUtils.result(OrderStatusEnum.LOCKED_STOCK_SUCCESS, null);
    }
}
