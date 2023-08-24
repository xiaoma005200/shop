package com.xiaoma.service;

import com.xiaoma.pojo.SkuInfo;
import org.apache.ibatis.annotations.Param;

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

}
