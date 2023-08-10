package com.xiaoma.service;

import com.xiaoma.pojo.BaseCatalog1;

import java.util.List;

/**
 * 商品分类服务接口
 */
public interface ProductCatagoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    List<BaseCatalog1> findAll();
}
