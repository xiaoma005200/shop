package com.xiaoma.service;

import com.xiaoma.pojo.SkuInfo;

/**
 * 商品SKU接口
 */
public interface SKUService {

    /**
     * 保存SKU信息
     * @param skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);
}
