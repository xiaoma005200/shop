package com.xiaoma.service.impl;

import com.xiaoma.mapper.BaseCatalog1Mapper;
import com.xiaoma.pojo.BaseCatalog1;
import com.xiaoma.pojo.BaseCatalog1Example;
import com.xiaoma.service.ProductCatagoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCatalogServiceImpl implements ProductCatagoryService {

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Override
    public List<BaseCatalog1> findAll() {
        return baseCatalog1Mapper.selectByExample(new BaseCatalog1Example());
    }
}
