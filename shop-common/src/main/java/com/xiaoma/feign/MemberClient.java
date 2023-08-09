package com.xiaoma.feign;

import com.xiaoma.pojo.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("shop-user-provider")
public interface MemberClient {

    @GetMapping("/shop/member/{id}")
    Member findById(@PathVariable("id") Long id);

    @GetMapping("/shop/member/findAll")
    List<Member> findAll();

    @GetMapping("/shop/member/findByName")
    List<Member> findByUsername(@RequestParam("username") String username);

}
