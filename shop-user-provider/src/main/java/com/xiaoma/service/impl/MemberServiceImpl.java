package com.xiaoma.service.impl;

import com.xiaoma.mapper.generate.MemberMapper;
import com.xiaoma.mapper.generate.MemberReceiveAddressMapper;
import com.xiaoma.pojo.Member;
import com.xiaoma.pojo.MemberExample;
import com.xiaoma.pojo.MemberReceiveAddress;
import com.xiaoma.pojo.MemberReceiveAddressExample;
import com.xiaoma.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberReceiveAddressMapper memberReceiveAddressMapper;

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
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andUsernameLike("%" + username + "%");
        return memberMapper.selectByExample(memberExample);

    }

    @Override
    public Member findByUsernameAndPwd(Member member) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andUsernameEqualTo(member.getUsername()).andPasswordEqualTo(member.getPassword());
        List<Member> members = memberMapper.selectByExample(memberExample);
        return CollectionUtils.isEmpty(members) ? null : members.get(0);
    }

    @Override
    public List<MemberReceiveAddress> getReceiveAddressByMemberId(Long memberId) {
        MemberReceiveAddressExample memberReceiveAddressExample = new MemberReceiveAddressExample();
        memberReceiveAddressExample.createCriteria().andMemberIdEqualTo(memberId);
        return memberReceiveAddressMapper.selectByExample(memberReceiveAddressExample);
    }

    @Override
    public MemberReceiveAddress getReceiveAddressByAddrId(Long addressId) {
        return memberReceiveAddressMapper.selectByPrimaryKey(addressId);
    }
}
