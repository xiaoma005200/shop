package com.xiaoma.service;

import com.xiaoma.pojo.Member;

import java.util.List;

public interface MemberService {

    /**
     * 根据id查询member
     * @param id
     * @return
     */
    Member findById(Long id);

    /**
     * 查询所有member
     * @return
     */
    List<Member> findAll();

    /**
     * 根据姓名模糊查询所有匹配的member
     * @param username
     * @return
     */
    List<Member> findByUsername(String username);
}
