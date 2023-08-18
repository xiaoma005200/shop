package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.SkuImage;
import com.xiaoma.pojo.SkuImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuImageMapper {
    long countByExample(SkuImageExample example);

    int deleteByExample(SkuImageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SkuImage record);

    int insertSelective(SkuImage record);

    List<SkuImage> selectByExample(SkuImageExample example);

    SkuImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SkuImage record, @Param("example") SkuImageExample example);

    int updateByExample(@Param("record") SkuImage record, @Param("example") SkuImageExample example);

    int updateByPrimaryKeySelective(SkuImage record);

    int updateByPrimaryKey(SkuImage record);
}