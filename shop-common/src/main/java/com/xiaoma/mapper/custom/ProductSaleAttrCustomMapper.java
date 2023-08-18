package com.xiaoma.mapper.custom;


import com.xiaoma.mapper.generate.ProductSaleAttrMapper;
import com.xiaoma.pojo.ProductSaleAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义的商品销售属性Mapper接口
 */
public interface ProductSaleAttrCustomMapper extends ProductSaleAttrMapper {
    /**
     * @Param注解将来为了映射mapper文件中的#{xxx}
     * @param spuId
     * @param skuId
     * @return
     */
    List<ProductSaleAttr> selectSPUSaleAttrAndCheck(@Param("spuId") Integer spuId, @Param("skuId") Integer skuId);
}
