package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.ProductSaleAttr;
import com.xiaoma.pojo.ProductSaleAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSaleAttrMapper {
    long countByExample(ProductSaleAttrExample example);

    int deleteByExample(ProductSaleAttrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductSaleAttr record);

    int insertSelective(ProductSaleAttr record);

    List<ProductSaleAttr> selectByExample(ProductSaleAttrExample example);

    ProductSaleAttr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductSaleAttr record, @Param("example") ProductSaleAttrExample example);

    int updateByExample(@Param("record") ProductSaleAttr record, @Param("example") ProductSaleAttrExample example);

    int updateByPrimaryKeySelective(ProductSaleAttr record);

    int updateByPrimaryKey(ProductSaleAttr record);
}