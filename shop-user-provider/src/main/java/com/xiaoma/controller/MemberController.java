package com.xiaoma.controller;

import com.xiaoma.pojo.Member;
import com.xiaoma.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * 根据id查询会员
     * @param id 会员id
     * @return 单个会员信息
     */
    @GetMapping("/{id}")
    public Member findById(@PathVariable Long id){
        return memberService.findById(id);
    }

    /**
     * 查询所有会员
     * @return 所有会员信息列表
     */
    @GetMapping("/findAll")
    public List<Member> findAll(){
        return memberService.findAll();
    }

    /**
     * 根据名字模糊匹配
     * @param username 会员名字
     * @return 返回模糊匹配上的会员信息列表
     */
    @GetMapping("/findByUsername")
    public List<Member> findByUsername(String username){
        return memberService.findByUsername(username);
    }
}
