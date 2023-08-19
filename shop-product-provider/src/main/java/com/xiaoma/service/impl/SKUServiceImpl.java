package com.xiaoma.service.impl;

import com.xiaoma.mapper.generate.SkuAttrValueMapper;
import com.xiaoma.mapper.generate.SkuImageMapper;
import com.xiaoma.mapper.generate.SkuInfoMapper;
import com.xiaoma.mapper.generate.SkuSaleAttrValueMapper;
import com.xiaoma.pojo.SkuImageExample;
import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.service.SKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SKUServiceImpl implements SKUService {

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    SkuImageMapper skuImageMapper;

    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        // 1.保存SKU基本信息
        skuInfoMapper.insertSelective(skuInfo);

        // 2.保存图片信息
        skuInfo.getSkuImageList().forEach(skuImage -> {
            skuImage.setSkuId(skuInfo.getId());
            skuImageMapper.insertSelective(skuImage);
        });

        // 3.保存SKU对应的属性列表
        skuInfo.getSkuAttrValueList().forEach(skuAttrValue -> {
            skuAttrValue.setSkuId(skuInfo.getId());
            skuAttrValueMapper.insertSelective(skuAttrValue);
        });

        //4.保存SKU对应的销售属性列表
        skuInfo.getSkuSaleAttrValueList().forEach(skuSaleAttrValue -> {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
        });

    }

    @Override
    public SkuInfo findBySkuInfoId(Integer skuInfoId) {
        // 1.查询Sku的基本信息
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuInfoId.longValue());

        // 2.查询sku对应的图片列表
        SkuImageExample skuImageExample = new SkuImageExample();
        skuImageExample.createCriteria().andSkuIdEqualTo(skuInfoId.longValue());
        skuInfo.setSkuImageList(skuImageMapper.selectByExample(skuImageExample));
        return skuInfo;
    }
}
