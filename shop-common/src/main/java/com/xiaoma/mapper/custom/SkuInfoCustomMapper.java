package com.xiaoma.mapper.custom;


import com.xiaoma.mapper.generate.SkuInfoMapper;
import com.xiaoma.pojo.SkuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuInfoCustomMapper extends SkuInfoMapper {
    /**
     *
     * @param spuId
     * @return 根据spuId查询其下所有的sku与它的销售属性值的映射
     */
    List<SkuInfo> selectSkuSaleAttrValuesBySpuId(@Param("spuId") Long spuId);


    String selectBySaleAttrValueIds(@Param("saleAttrValueIds") String saleAttrValueIds);
}
