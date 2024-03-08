package com.xiaoma.service;

import com.xiaoma.exception.LockStockException;
import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.vo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品SKU接口
 */
public interface SKUService {

    /**
     * 保存SKU信息
     * @param skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 根据SKUid查询商品SKU信息
     * @param skuInfoId
     * @return
     */
    SkuInfo findBySkuInfoId(Integer skuInfoId);

    /**
     *
     * @param spuId
     * @return 根据spuId查询其下所有 sku与它的销售属性值 的映射
     */
    Map<String,Long> findSkuSaleAttrValuesBySpuId(Long spuId);

    /**
     * 根据三级分类Id查询所有的skuInfo
     * @param catalog3Id
     * @return
     */
    List<SkuInfo> findAll(Long catalog3Id);

    /**
     * 根据saleValueIds查询SkuId
     * @param saleAttrValueIds
     * @return
     */
    String selectSkuIdByValueIds(String saleAttrValueIds);

    /***
     * 根据skuId查询价格
     * @param skuId
     * @return
     */
    BigDecimal findPriceBySkuId(Long skuId);

    /**
     * 根据skuIds获取每个sku库存状态
     * 128=true  128号商品有库存
     * 132=false 132号商品没有库存
     * @param skuIds
     * @return
     */
    Map<Long,Boolean> getSkuStock(List<Long> skuIds);

    /**
     * 每个购物项都要去锁定库存
     * @param orderItemList
     */
    void orderLockStock(List<OrderItem> orderItemList) throws LockStockException;

}
