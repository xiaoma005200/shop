package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.WareSku;
import com.xiaoma.pojo.WareSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WareSkuMapper {
    long countByExample(WareSkuExample example);

    int deleteByExample(WareSkuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WareSku record);

    int insertSelective(WareSku record);

    List<WareSku> selectByExample(WareSkuExample example);

    WareSku selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WareSku record, @Param("example") WareSkuExample example);

    int updateByExample(@Param("record") WareSku record, @Param("example") WareSkuExample example);

    int updateByPrimaryKeySelective(WareSku record);

    int updateByPrimaryKey(WareSku record);
}