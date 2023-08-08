package com.xiaoma.mapper;

import com.xiaoma.pojo.WareOrderTask;
import com.xiaoma.pojo.WareOrderTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WareOrderTaskMapper {
    long countByExample(WareOrderTaskExample example);

    int deleteByExample(WareOrderTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WareOrderTask record);

    int insertSelective(WareOrderTask record);

    List<WareOrderTask> selectByExample(WareOrderTaskExample example);

    WareOrderTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WareOrderTask record, @Param("example") WareOrderTaskExample example);

    int updateByExample(@Param("record") WareOrderTask record, @Param("example") WareOrderTaskExample example);

    int updateByPrimaryKeySelective(WareOrderTask record);

    int updateByPrimaryKey(WareOrderTask record);
}