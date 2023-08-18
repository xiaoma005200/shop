package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.UndoLog;
import com.xiaoma.pojo.UndoLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UndoLogMapper {
    long countByExample(UndoLogExample example);

    int deleteByExample(UndoLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UndoLog record);

    int insertSelective(UndoLog record);

    List<UndoLog> selectByExample(UndoLogExample example);

    UndoLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UndoLog record, @Param("example") UndoLogExample example);

    int updateByExample(@Param("record") UndoLog record, @Param("example") UndoLogExample example);

    int updateByPrimaryKeySelective(UndoLog record);

    int updateByPrimaryKey(UndoLog record);
}