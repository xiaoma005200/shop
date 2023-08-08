package com.xiaoma.mapper;

import com.xiaoma.pojo.BaseAttrValue;
import com.xiaoma.pojo.BaseAttrValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseAttrValueMapper {
    long countByExample(BaseAttrValueExample example);

    int deleteByExample(BaseAttrValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseAttrValue record);

    int insertSelective(BaseAttrValue record);

    List<BaseAttrValue> selectByExample(BaseAttrValueExample example);

    BaseAttrValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseAttrValue record, @Param("example") BaseAttrValueExample example);

    int updateByExample(@Param("record") BaseAttrValue record, @Param("example") BaseAttrValueExample example);

    int updateByPrimaryKeySelective(BaseAttrValue record);

    int updateByPrimaryKey(BaseAttrValue record);
}