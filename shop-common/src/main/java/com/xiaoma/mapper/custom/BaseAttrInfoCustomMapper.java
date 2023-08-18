package com.xiaoma.mapper.custom;

import com.xiaoma.mapper.generate.BaseAttrInfoMapper;
import com.xiaoma.pojo.BaseAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAttrInfoCustomMapper extends BaseAttrInfoMapper {
    List<BaseAttrInfo> selectBaseAttrByValueIds(@Param("valueIds") List<Integer> valueIds);
}
