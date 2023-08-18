package com.xiaoma.mapper.custom;


import com.xiaoma.mapper.generate.WareSkuMapper;
import org.apache.ibatis.annotations.Param;

public interface WareSkuCustomMapper extends WareSkuMapper {
    /**
     * 根据skuId来获取当前sku的库存信息
     * @param skuId
     * @return
     */
    Long getSkuStock(@Param("skuid") Long skuId);


    /**
     * 根据skuId以及wareId来解锁库存
     * @param skuId
     * @param wareId
     * @param num
     */
    void releaseStock(@Param("skuId") Long skuId,@Param("wareId") Long wareId, @Param("num") Integer num);
}
