package com.xiaoma.service;

import com.xiaoma.pojo.ProductInfo;

import java.util.List;

/**
 * 商品SPU接口
 */
public interface SPUService {

    /**
     * 根据三级分类id查询所有的SPU(标准商品单元)
     * @param catalog3Id 三级分类id
     * @returnn 该三级分类下的商品SPU列表
     */
    List<ProductInfo> findAll(Integer catalog3Id);
}
