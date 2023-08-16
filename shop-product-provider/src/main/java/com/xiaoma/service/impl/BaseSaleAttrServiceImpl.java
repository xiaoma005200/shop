package com.xiaoma.service.impl;

import com.xiaoma.mapper.BaseSaleAttrMapper;
import com.xiaoma.pojo.BaseSaleAttr;
import com.xiaoma.pojo.BaseSaleAttrExample;
import com.xiaoma.service.BaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseSaleAttrServiceImpl implements BaseSaleAttrService {

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;

    @Override
    public List<BaseSaleAttr> findAllSaleAttr() {
        return baseSaleAttrMapper.selectByExample(new BaseSaleAttrExample());
    }
}
