package com.xiaoma.service;

import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.BaseAttrValue;

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

    /**
     * 接收页面的属性和属性值信息
     * @param baseAttrInfo 接收页面的属性和属性值信息
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 根据属性id查询所有的属性值
     * @param attrId 属性id
     * @return 属性值列表
     */
    List<BaseAttrValue> getAttrValueByAttrId(Integer attrId);
}
