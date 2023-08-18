package com.xiaoma.service.impl;

import com.xiaoma.mapper.generate.MemberMapper;
import com.xiaoma.pojo.Member;
import com.xiaoma.pojo.MemberExample;
import com.xiaoma.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public Member findById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.selectByExample(new MemberExample());
    }

    @Override
    public List<Member> findByUsername(String username) {
        // 1.构造查询条件
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andUsernameLike("%" + username + "%");

        // 2.传入查询条件,执行查询
        return memberMapper.selectByExample(memberExample);
    }
}
