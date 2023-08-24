package com.xiaoma.mapper.custom;


import com.xiaoma.mapper.generate.ProductSaleAttrMapper;
import com.xiaoma.pojo.ProductSaleAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  自定义mapper接口,继承ProductSaleAttrMapper可以直接使用自动生成的方法
 */
public interface ProductSaleAttrCustomMapper extends ProductSaleAttrMapper {
    /**
     * @Param注解将来为了映射mapper文件中的#{xxx}
     * @return 根据spuId和skuId查询销售属性列表(列表的isChecked字段代表含销售属性选中状态 1选中 0未选中)
     * @param spuId
     * @param skuId
     * @return
     */
    List<ProductSaleAttr> selectSPUSaleAttrAndCheck(@Param("spuId") Integer spuId, @Param("skuId") Integer skuId);
}
