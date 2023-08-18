package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.ShopMessage;
import com.xiaoma.pojo.ShopMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopMessageMapper {
    long countByExample(ShopMessageExample example);

    int deleteByExample(ShopMessageExample example);

    int deleteByPrimaryKey(Integer messagaeId);

    int insert(ShopMessage record);

    int insertSelective(ShopMessage record);

    List<ShopMessage> selectByExample(ShopMessageExample example);

    ShopMessage selectByPrimaryKey(Integer messagaeId);

    int updateByExampleSelective(@Param("record") ShopMessage record, @Param("example") ShopMessageExample example);

    int updateByExample(@Param("record") ShopMessage record, @Param("example") ShopMessageExample example);

    int updateByPrimaryKeySelective(ShopMessage record);

    int updateByPrimaryKey(ShopMessage record);
}