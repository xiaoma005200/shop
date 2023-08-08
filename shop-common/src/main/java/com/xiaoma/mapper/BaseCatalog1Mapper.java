package com.xiaoma.mapper;

import com.xiaoma.pojo.BaseCatalog1;
import com.xiaoma.pojo.BaseCatalog1Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseCatalog1Mapper {
    long countByExample(BaseCatalog1Example example);

    int deleteByExample(BaseCatalog1Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseCatalog1 record);

    int insertSelective(BaseCatalog1 record);

    List<BaseCatalog1> selectByExample(BaseCatalog1Example example);

    BaseCatalog1 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseCatalog1 record, @Param("example") BaseCatalog1Example example);

    int updateByExample(@Param("record") BaseCatalog1 record, @Param("example") BaseCatalog1Example example);

    int updateByPrimaryKeySelective(BaseCatalog1 record);

    int updateByPrimaryKey(BaseCatalog1 record);
}