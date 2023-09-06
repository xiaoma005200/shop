package com.xiaoma.controller;

import com.xiaoma.pojo.Member;
import com.xiaoma.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/{id}")
    public Member findById(@PathVariable Long id){
        return memberService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Member> findAll(){
        return memberService.findAll();
    }

    @GetMapping("/findByName")
    public List<Member> findByUsername(String username){
        return memberService.findByUsername(username);
    }

    @PostMapping("/findByUsernameAndPwd")
    public Member findByUsernameAndPwd(@RequestBody Member member){
        return memberService.findByUsernameAndPwd(member);
    }
}
