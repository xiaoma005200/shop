package com.xiaoma.service.impl;

import com.xiaoma.mapper.custom.SkuInfoCustomMapper;
import com.xiaoma.mapper.generate.SkuAttrValueMapper;
import com.xiaoma.mapper.generate.SkuImageMapper;
import com.xiaoma.mapper.generate.SkuInfoMapper;
import com.xiaoma.mapper.generate.SkuSaleAttrValueMapper;
import com.xiaoma.pojo.*;
import com.xiaoma.service.SKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    SkuInfoCustomMapper skuInfoCustomMapper;

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //1.保存sku基本信息=>sku_info
        skuInfoMapper.insertSelective(skuInfo);

        //2.保存sku对应的图片信息=>sku_image
        skuInfo.getSkuImageList().forEach(skuImage -> {
            skuImage.setSkuId(skuInfo.getId());
            skuImageMapper.insertSelective(skuImage);
        });

        //3.保存sku对应的基本属性(基本属性值)=>sku_attr_value
        skuInfo.getSkuAttrValueList().forEach(skuAttrValue -> {
            skuAttrValue.setSkuId(skuInfo.getId());
            skuAttrValueMapper.insertSelective(skuAttrValue);
        });

        //4.保存sku对应的销售属性(销售属性值)=>sku_sale_attr_value
        skuInfo.getSkuSaleAttrValueList().forEach(skuSaleAttrValue -> {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
        });

    }

    @Override
    public SkuInfo findBySkuInfoId(Integer skuInfoId) {
        // 1.查询Sku的基本信息==>sku_info表
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuInfoId.longValue());

        // 2.查询sku对应的图片列表==>sku_image表
        SkuImageExample skuImageExample = new SkuImageExample();
        skuImageExample.createCriteria().andSkuIdEqualTo(skuInfoId.longValue());
        List<SkuImage> skuImages = skuImageMapper.selectByExample(skuImageExample);

        // 3.查询sku对应的销售信息==>sku_sale_attr_value
        SkuSaleAttrValueExample skuSaleAttrValueExample = new SkuSaleAttrValueExample();
        skuSaleAttrValueExample.createCriteria().andSkuIdEqualTo(skuInfoId.longValue());
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuSaleAttrValueMapper.selectByExample(skuSaleAttrValueExample);

        // 4.将skuImages设置到skuInfo中
        skuInfo.setSkuImageList(skuImages);

        // 5.将skuSaleAttrValueList设置到skuInfo中
        skuInfo.setSkuSaleAttrValueList(skuSaleAttrValueList);

        return skuInfo;
    }

    @Override
    public Map<String, Long> findSkuSaleAttrValuesBySpuId(Long spuId) {
        // 1.查询出skuInfo和销售属性值信息
        List<SkuInfo> skuInfoList = skuInfoCustomMapper.selectSkuSaleAttrValuesBySpuId(spuId);

        //2.构造map映射表
        /**
         * sale_attr_value_id(key)     skuId(value)
         * 328#331                     165
         * 329#330                     167
         * 328#330                     164
         * 329#331                     168
         * 329#332                     169
         * 328#332                     166
         */
        Map<String, Long> map = skuInfoList.stream().collect(Collectors.toMap(skuInfo ->
                                skuInfo.getSkuSaleAttrValueList()
                                        .stream()
                                        .map(skuSaleAttrValue ->
                                                skuSaleAttrValue.getSaleAttrValueId() + "")
                                        .collect(Collectors.joining("#")),SkuInfo::getId));
        return map;
    }
}
