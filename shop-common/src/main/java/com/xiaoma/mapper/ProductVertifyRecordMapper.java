package com.xiaoma.mapper;

import com.xiaoma.pojo.ProductVertifyRecord;
import com.xiaoma.pojo.ProductVertifyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductVertifyRecordMapper {
    long countByExample(ProductVertifyRecordExample example);

    int deleteByExample(ProductVertifyRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductVertifyRecord record);

    int insertSelective(ProductVertifyRecord record);

    List<ProductVertifyRecord> selectByExample(ProductVertifyRecordExample example);

    ProductVertifyRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductVertifyRecord record, @Param("example") ProductVertifyRecordExample example);

    int updateByExample(@Param("record") ProductVertifyRecord record, @Param("example") ProductVertifyRecordExample example);

    int updateByPrimaryKeySelective(ProductVertifyRecord record);

    int updateByPrimaryKey(ProductVertifyRecord record);
}