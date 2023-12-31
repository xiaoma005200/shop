package com.xiaoma.service;

import com.xiaoma.pojo.BaseSaleAttr;

import java.util.List;

/**
 * 操作商品销售属性接口
 */
public interface BaseSaleAttrService {

    /**
     * 查询商品基本销售属性(baseSaleAttr表)
     * @return
     */
    List<BaseSaleAttr> findAllBaseSaleAttr();
}
