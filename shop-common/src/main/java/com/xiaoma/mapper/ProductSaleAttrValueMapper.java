package com.xiaoma.mapper;

import com.xiaoma.pojo.ProductSaleAttrValue;
import com.xiaoma.pojo.ProductSaleAttrValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSaleAttrValueMapper {
    long countByExample(ProductSaleAttrValueExample example);

    int deleteByExample(ProductSaleAttrValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductSaleAttrValue record);

    int insertSelective(ProductSaleAttrValue record);

    List<ProductSaleAttrValue> selectByExample(ProductSaleAttrValueExample example);

    ProductSaleAttrValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductSaleAttrValue record, @Param("example") ProductSaleAttrValueExample example);

    int updateByExample(@Param("record") ProductSaleAttrValue record, @Param("example") ProductSaleAttrValueExample example);

    int updateByPrimaryKeySelective(ProductSaleAttrValue record);

    int updateByPrimaryKey(ProductSaleAttrValue record);
}