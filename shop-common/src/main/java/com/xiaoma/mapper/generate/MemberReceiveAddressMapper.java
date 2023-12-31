package com.xiaoma.mapper.generate;

import com.xiaoma.pojo.MemberReceiveAddress;
import com.xiaoma.pojo.MemberReceiveAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberReceiveAddressMapper {
    long countByExample(MemberReceiveAddressExample example);

    int deleteByExample(MemberReceiveAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberReceiveAddress record);

    int insertSelective(MemberReceiveAddress record);

    List<MemberReceiveAddress> selectByExample(MemberReceiveAddressExample example);

    MemberReceiveAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberReceiveAddress record, @Param("example") MemberReceiveAddressExample example);

    int updateByExample(@Param("record") MemberReceiveAddress record, @Param("example") MemberReceiveAddressExample example);

    int updateByPrimaryKeySelective(MemberReceiveAddress record);

    int updateByPrimaryKey(MemberReceiveAddress record);
}