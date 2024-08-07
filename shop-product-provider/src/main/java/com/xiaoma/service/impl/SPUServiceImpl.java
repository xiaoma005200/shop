package com.xiaoma.service.impl;

import com.xiaoma.mapper.custom.ProductSaleAttrCustomMapper;
import com.xiaoma.mapper.generate.*;
import com.xiaoma.pojo.*;
import com.xiaoma.service.SPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SPUServiceImpl implements SPUService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    ProductImageMapper productImageMapper;

    @Autowired
    ProductSaleAttrMapper productSaleAttrMapper;

    @Autowired
    ProductSaleAttrValueMapper productSaleAttrValueMapper;

    @Autowired
    ProductSaleAttrCustomMapper productSaleAttrCustomMapper;

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<ProductInfo> findAll(Integer catalog3Id) {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.createCriteria().andCatalog3IdEqualTo(catalog3Id.longValue());
        return productInfoMapper.selectByExample(productInfoExample);
    }

    @Override
    public void saveProductInfo(ProductInfo productInfo) {
        // 1.保存SPU信息,插入成功,productInfo具有ID==>product_info
        productInfoMapper.insertSelective(productInfo);
        // 2.保存SPU对应的图片信息==>product_image
        productInfo.getProductImageList().forEach(productImage -> {
            productImage.setProductId(productInfo.getId());
            productImage.setImgUrl("http://175.178.16.179:8888/" + productImage.getImgUrl());
            productImageMapper.insertSelective(productImage);
        });
        //3.保存销售属性相关信息==>product_sale_attr
        productInfo.getProductSaleAttrList().forEach(
                productSaleAttr -> {
                    productSaleAttr.setProductId(productInfo.getId());
                    productSaleAttrMapper.insertSelective(productSaleAttr);
                    //4.保存销售属性对应的属性值==>product_sale_attr_value
                    productSaleAttr.getProductSaleAttrValueList().forEach(
                            productSaleAttrValue -> {
                                productSaleAttrValue.setProductId(productInfo.getId());
                                productSaleAttrValueMapper.insertSelective(productSaleAttrValue);
                            }
                    );
                }
        );
    }

    @Override
    public List<ProductImage> findImagesByPid(Integer spuId) {
        ProductImageExample productImageExample = new ProductImageExample();
        productImageExample.createCriteria().andProductIdEqualTo(spuId.longValue());
        return productImageMapper.selectByExample(productImageExample);
    }

    @Override
    public List<ProductSaleAttr> findSaleAttrByPid(Integer spuId) {
        // 1.根据spuId查询对应的销售属性
        ProductSaleAttrExample productSaleAttrExample = new ProductSaleAttrExample();
        productSaleAttrExample.createCriteria().andProductIdEqualTo(spuId.longValue());
        List<ProductSaleAttr> productSaleAttrList = productSaleAttrMapper.selectByExample(productSaleAttrExample);

        // 2.根据销售属性查询对应的销售属性值
        productSaleAttrList.forEach(productSaleAttr -> {
            // 2.1 查询spu对应的所有的属性值
            ProductSaleAttrValueExample productSaleAttrValueExample = new ProductSaleAttrValueExample();
            productSaleAttrValueExample.createCriteria()
                    .andProductIdEqualTo(spuId.longValue())  //该sku的属性值是哪个spu下的
                    .andSaleAttrIdEqualTo(productSaleAttr.getSaleAttrId());  //是sku的哪个属性的属性值

            List<ProductSaleAttrValue> productSaleAttrValues = productSaleAttrValueMapper.selectByExample(productSaleAttrValueExample);
            // 2.2 将属性值设置到每个productSaleAttr
            productSaleAttr.setProductSaleAttrValueList(productSaleAttrValues);

        });
        return productSaleAttrList;
    }

    @Override
    public List<ProductSaleAttr> findSPUSaleAttrAndCheck(Integer spuId, Integer skuId) {
        return productSaleAttrCustomMapper.selectSPUSaleAttrAndCheck(spuId,skuId);
    }

    @Override
    public ProductInfo findSPUBySkuId(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuId);
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(skuInfo.getProductId());
        if (!(productInfo.getTmId()==null)) { // 空 false
            Brand brand = brandMapper.selectByPrimaryKey(productInfo.getTmId());
            productInfo.setBrand(brand);
        }
        return productInfo;
    }


}
