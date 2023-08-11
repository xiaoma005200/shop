package com.xiaoma.service.impl;

import com.xiaoma.mapper.BaseAttrInfoMapper;
import com.xiaoma.mapper.BaseAttrValueMapper;
import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.BaseAttrInfoExample;
import com.xiaoma.pojo.BaseAttrValue;
import com.xiaoma.service.ProductAttrInfoService;
import com.xiaoma.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttrInfoServiceImpl implements ProductAttrInfoService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id) {
        BaseAttrInfoExample baseAttrInfoExample = new BaseAttrInfoExample();
        baseAttrInfoExample.createCriteria().andCatalog3IdEqualTo(catalog3Id.longValue());
        return baseAttrInfoMapper.selectByExample(baseAttrInfoExample);
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 1.将属性相关的信息保存到base_attr_info表中
        baseAttrInfoMapper.insertSelective(baseAttrInfo);

        // 2.将属性对应的所有属性值保存到base_attr_value中
        /*for (BaseAttrValue baseAttrValue : baseAttrInfo.getAttrValueList()) {
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insertSelective(baseAttrValue);
        }*/
        baseAttrInfo.getAttrValueList().forEach(baseAttrValue -> {
           baseAttrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insertSelective(baseAttrValue);
        });
    }
}
