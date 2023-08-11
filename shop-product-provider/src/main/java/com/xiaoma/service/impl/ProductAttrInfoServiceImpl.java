package com.xiaoma.service.impl;

import com.xiaoma.mapper.BaseAttrInfoMapper;
import com.xiaoma.pojo.BaseAttrInfo;
import com.xiaoma.pojo.BaseAttrInfoExample;
import com.xiaoma.service.ProductAttrInfoService;
import com.xiaoma.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAttrInfoServiceImpl implements ProductAttrInfoService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Override
    public List<BaseAttrInfo> getAttrByCatalog3Id(Integer catalog3Id) {
        BaseAttrInfoExample baseAttrInfoExample = new BaseAttrInfoExample();
        baseAttrInfoExample.createCriteria().andCatalog3IdEqualTo(catalog3Id.longValue());
        return baseAttrInfoMapper.selectByExample(baseAttrInfoExample);
    }
}
