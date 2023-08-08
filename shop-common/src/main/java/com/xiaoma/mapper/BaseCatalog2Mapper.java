package com.xiaoma.mapper;

import com.xiaoma.pojo.BaseCatalog2;
import com.xiaoma.pojo.BaseCatalog2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseCatalog2Mapper {
    long countByExample(BaseCatalog2Example example);

    int deleteByExample(BaseCatalog2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseCatalog2 record);

    int insertSelective(BaseCatalog2 record);

    List<BaseCatalog2> selectByExample(BaseCatalog2Example example);

    BaseCatalog2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseCatalog2 record, @Param("example") BaseCatalog2Example example);

    int updateByExample(@Param("record") BaseCatalog2 record, @Param("example") BaseCatalog2Example example);

    int updateByPrimaryKeySelective(BaseCatalog2 record);

    int updateByPrimaryKey(BaseCatalog2 record);
}