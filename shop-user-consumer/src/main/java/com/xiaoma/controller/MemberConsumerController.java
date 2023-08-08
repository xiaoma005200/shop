package com.xiaoma.controller;

import com.xiaoma.feign.MemberClient;
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
public class MemberConsumerController {

    @Autowired
    MemberClient memberClient;

    @GetMapping("/{id}")
    public Member findById(@PathVariable Long id){
        return memberClient.findById(id);
    }

    @GetMapping("/findAll")
    public List<Member> findAll(){
        return memberClient.findAll();
    }

    @GetMapping("/findByUsername")
    public List<Member> findByUsername(String username){
        return memberClient.findByUsername(username);
    }

}
