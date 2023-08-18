package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.SkuAttrValue;
import com.xiaoma.pojo.SkuAttrValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuAttrValueMapper {
    long countByExample(SkuAttrValueExample example);

    int deleteByExample(SkuAttrValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SkuAttrValue record);

    int insertSelective(SkuAttrValue record);

    List<SkuAttrValue> selectByExample(SkuAttrValueExample example);

    SkuAttrValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SkuAttrValue record, @Param("example") SkuAttrValueExample example);

    int updateByExample(@Param("record") SkuAttrValue record, @Param("example") SkuAttrValueExample example);

    int updateByPrimaryKeySelective(SkuAttrValue record);

    int updateByPrimaryKey(SkuAttrValue record);
}