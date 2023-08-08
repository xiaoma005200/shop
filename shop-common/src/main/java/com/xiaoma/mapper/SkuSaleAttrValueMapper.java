package com.xiaoma.mapper;

import com.xiaoma.pojo.SkuSaleAttrValue;
import com.xiaoma.pojo.SkuSaleAttrValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuSaleAttrValueMapper {
    long countByExample(SkuSaleAttrValueExample example);

    int deleteByExample(SkuSaleAttrValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SkuSaleAttrValue record);

    int insertSelective(SkuSaleAttrValue record);

    List<SkuSaleAttrValue> selectByExample(SkuSaleAttrValueExample example);

    SkuSaleAttrValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SkuSaleAttrValue record, @Param("example") SkuSaleAttrValueExample example);

    int updateByExample(@Param("record") SkuSaleAttrValue record, @Param("example") SkuSaleAttrValueExample example);

    int updateByPrimaryKeySelective(SkuSaleAttrValue record);

    int updateByPrimaryKey(SkuSaleAttrValue record);
}