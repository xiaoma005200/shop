package com.xiaoma.controller;

import com.xiaoma.feign.MemberClient;
import com.xiaoma.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findByName")
    public List<Member> findByName(String username){
        return memberClient.findByUsername(username);
    }

}
