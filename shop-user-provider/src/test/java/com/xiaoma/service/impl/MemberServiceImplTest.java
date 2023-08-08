package com.xiaoma.service.impl;

import com.xiaoma.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    void findById() {
        System.out.println(memberService.findById(1L));
    }

    @Test
    void findAll() {
        memberService.findAll().forEach(System.out::println);
    }

    @Test
    void findByUsername() {
        memberService.findByUsername("li").forEach(System.out::println);
    }
}