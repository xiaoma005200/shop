package com.xiaoma.service;

import com.xiaoma.pojo.Member;
import com.xiaoma.pojo.MemberReceiveAddress;

import java.util.List;

/**
 * 会员查询接口
 */
public interface MemberService {

    /**
     * 根据id查询member
     * @param id 会员id
     * @return 单个会员
     */
    Member findById(Long id);

    /**
     * 查询所有member
     * @return 所有的会员集合
     */
    List<Member> findAll();

    /**
     * 根据姓名模糊查询所有匹配的member
     * @param username 会员名
     * @return 根据会员名模糊匹配的会员集合
     */
    List<Member> findByUsername(String username);

    /**
     * 根据用户名密码查询用户
     * @param member 封装用户名密码等信息
     * @return member对象
     */
    Member findByUsernameAndPwd(Member member);

    /**
     * 根据memberId获取用户收货地址
     * @param memberId
     * @return
     */
    List<MemberReceiveAddress> getReceiveAddressByMemberId(Long memberId);

    /**
     * 根据addressId获取收货地址信息
     * @param addressId
     * @return
     */
    MemberReceiveAddress getReceiveAddressByAddrId(Long addressId);
}
