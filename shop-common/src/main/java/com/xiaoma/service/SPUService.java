package com.xiaoma.service;

import com.xiaoma.pojo.ProductImage;
import com.xiaoma.pojo.ProductInfo;
import com.xiaoma.pojo.ProductSaleAttr;

import java.util.List;

/**
 * 商品SPU接口
 */
public interface SPUService {

    /**
     * 根据三级分类id查询所有的SPU(标准商品单元)
     * @param catalog3Id 三级分类id
     * @returnn 该三级分类下的商品SPU列表
     */
    List<ProductInfo> findAll(Integer catalog3Id);

    /**
     * 查询SPU(待上传)下的所有图片
     * @param spuId SPU(product_Info)的id
     * @return 当前SPU下的所有图片
     */
    List<ProductImage> findImagesByPid(Integer spuId);

    /**
     * 查询SPU(待上传)的所有销售属性
     * @param spuId SPU(product_info)的id
     * @return 当前SPU对应的所有销售属性(包含销售属性值)
     */
    List<ProductSaleAttr> findSaleAttrByPid(Integer spuId);

    /**
     * 保存SPU(待上传)相关信息
     * @param productInfo
     */
    void saveSpuInfo(ProductInfo productInfo);

}
