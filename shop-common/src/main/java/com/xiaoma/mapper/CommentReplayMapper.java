package com.xiaoma.mapper;

import com.xiaoma.pojo.CommentReplay;
import com.xiaoma.pojo.CommentReplayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentReplayMapper {
    long countByExample(CommentReplayExample example);

    int deleteByExample(CommentReplayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentReplay record);

    int insertSelective(CommentReplay record);

    List<CommentReplay> selectByExample(CommentReplayExample example);

    CommentReplay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommentReplay record, @Param("example") CommentReplayExample example);

    int updateByExample(@Param("record") CommentReplay record, @Param("example") CommentReplayExample example);

    int updateByPrimaryKeySelective(CommentReplay record);

    int updateByPrimaryKey(CommentReplay record);
}