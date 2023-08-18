package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.SkuInfo;
import com.xiaoma.pojo.SkuInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuInfoMapper {
    long countByExample(SkuInfoExample example);

    int deleteByExample(SkuInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SkuInfo record);

    int insertSelective(SkuInfo record);

    List<SkuInfo> selectByExample(SkuInfoExample example);

    SkuInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SkuInfo record, @Param("example") SkuInfoExample example);

    int updateByExample(@Param("record") SkuInfo record, @Param("example") SkuInfoExample example);

    int updateByPrimaryKeySelective(SkuInfo record);

    int updateByPrimaryKey(SkuInfo record);
}