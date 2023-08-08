package com.xiaoma.mapper;

import com.xiaoma.pojo.WareInfo;
import com.xiaoma.pojo.WareInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WareInfoMapper {
    long countByExample(WareInfoExample example);

    int deleteByExample(WareInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WareInfo record);

    int insertSelective(WareInfo record);

    List<WareInfo> selectByExample(WareInfoExample example);

    WareInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WareInfo record, @Param("example") WareInfoExample example);

    int updateByExample(@Param("record") WareInfo record, @Param("example") WareInfoExample example);

    int updateByPrimaryKeySelective(WareInfo record);

    int updateByPrimaryKey(WareInfo record);
}