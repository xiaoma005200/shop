package com.xiaoma.mapper;

import com.xiaoma.pojo.WareOrderTaskDetail;
import com.xiaoma.pojo.WareOrderTaskDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WareOrderTaskDetailMapper {
    long countByExample(WareOrderTaskDetailExample example);

    int deleteByExample(WareOrderTaskDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WareOrderTaskDetail record);

    int insertSelective(WareOrderTaskDetail record);

    List<WareOrderTaskDetail> selectByExample(WareOrderTaskDetailExample example);

    WareOrderTaskDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WareOrderTaskDetail record, @Param("example") WareOrderTaskDetailExample example);

    int updateByExample(@Param("record") WareOrderTaskDetail record, @Param("example") WareOrderTaskDetailExample example);

    int updateByPrimaryKeySelective(WareOrderTaskDetail record);

    int updateByPrimaryKey(WareOrderTaskDetail record);
}