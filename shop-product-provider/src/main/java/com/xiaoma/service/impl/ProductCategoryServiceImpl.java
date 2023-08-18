package com.xiaoma.service.impl;

import com.xiaoma.mapper.generate.BaseCatalog1Mapper;
import com.xiaoma.mapper.generate.BaseCatalog2Mapper;
import com.xiaoma.mapper.generate.BaseCatalog3Mapper;
import com.xiaoma.pojo.*;
import com.xiaoma.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    BaseCatalog3Mapper baseCatalog3Mapper;

    @Override
    public List<BaseCatalog1> findAll() {
        return baseCatalog1Mapper.selectByExample(new BaseCatalog1Example());
    }

    @Override
    public List<BaseCatalog2> findByCatalog1Id(Integer catalog1Id) {
        BaseCatalog2Example baseCatalog2Example = new BaseCatalog2Example();
        baseCatalog2Example.createCriteria().andCatalog1IdEqualTo(catalog1Id);
        return baseCatalog2Mapper.selectByExample(baseCatalog2Example);
    }

    @Override
    public List<BaseCatalog3> findByCatalog2Id(Integer catalog2Id) {
        BaseCatalog3Example baseCatalog3Example = new BaseCatalog3Example();
        baseCatalog3Example.createCriteria().andCatalog2IdEqualTo(catalog2Id.longValue());
        return baseCatalog3Mapper.selectByExample(baseCatalog3Example);
    }

}
