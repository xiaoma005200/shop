package com.xiaoma.mapper;

import com.xiaoma.pojo.BaseSaleAttr;
import com.xiaoma.pojo.BaseSaleAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseSaleAttrMapper {
    long countByExample(BaseSaleAttrExample example);

    int deleteByExample(BaseSaleAttrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseSaleAttr record);

    int insertSelective(BaseSaleAttr record);

    List<BaseSaleAttr> selectByExample(BaseSaleAttrExample example);

    BaseSaleAttr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseSaleAttr record, @Param("example") BaseSaleAttrExample example);

    int updateByExample(@Param("record") BaseSaleAttr record, @Param("example") BaseSaleAttrExample example);

    int updateByPrimaryKeySelective(BaseSaleAttr record);

    int updateByPrimaryKey(BaseSaleAttr record);
}