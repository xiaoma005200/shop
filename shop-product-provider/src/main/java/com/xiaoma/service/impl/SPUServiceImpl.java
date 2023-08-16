package com.xiaoma.service.impl;

import com.xiaoma.mapper.ProductInfoMapper;
import com.xiaoma.pojo.ProductInfo;
import com.xiaoma.pojo.ProductInfoExample;
import com.xiaoma.service.SPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SPUServiceImpl implements SPUService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findAll(Integer catalog3Id) {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.createCriteria().andCatalog3IdEqualTo(catalog3Id.longValue());
        return productInfoMapper.selectByExample(productInfoExample);
    }
}
