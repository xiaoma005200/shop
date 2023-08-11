package com.xiaoma.service;

import com.xiaoma.pojo.BaseAttrInfo;

import java.util.List;

/**
 * 查询商品属性
 */
public interface ProductAttrInfoService {

    /**
     * 根据三级分类id查询所有属性
     * @param catalog3Id 三级分类id
     * @return 该三级分类id下的所有属性
     */
    List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id);
}
