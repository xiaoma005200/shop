package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.BaseCatalog3;
import com.xiaoma.pojo.BaseCatalog3Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseCatalog3Mapper {
    long countByExample(BaseCatalog3Example example);

    int deleteByExample(BaseCatalog3Example example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseCatalog3 record);

    int insertSelective(BaseCatalog3 record);

    List<BaseCatalog3> selectByExample(BaseCatalog3Example example);

    BaseCatalog3 selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseCatalog3 record, @Param("example") BaseCatalog3Example example);

    int updateByExample(@Param("record") BaseCatalog3 record, @Param("example") BaseCatalog3Example example);

    int updateByPrimaryKeySelective(BaseCatalog3 record);

    int updateByPrimaryKey(BaseCatalog3 record);
}