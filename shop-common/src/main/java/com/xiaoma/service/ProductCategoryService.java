package com.xiaoma.service;

import com.xiaoma.pojo.BaseCatalog1;
import com.xiaoma.pojo.BaseCatalog2;
import com.xiaoma.pojo.BaseCatalog3;

import java.util.List;

/**
 * 商品分类服务接口
 */
public interface ProductCategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    List<BaseCatalog1> findAll();

    /**
     * 根据一级分类id查询所有二级分类
     * @param catalog1Id 一级分类id
     * @return 一级分类下的所有二级分类
     */
    List<BaseCatalog2> findByCatalog1Id(Integer catalog1Id);

    /**
     * 根据二级分类id查询所有三级分类
     * @param catalog2Id 二级分类id
     * @return 二级分类下的所有三级分类
     */
    List<BaseCatalog3> findByCatalog2Id(Integer catalog2Id);
}
