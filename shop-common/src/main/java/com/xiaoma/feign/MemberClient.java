package com.xiaoma.feign;

import com.xiaoma.pojo.Member;
import com.xiaoma.pojo.MemberReceiveAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("shop-user-provider")
public interface MemberClient {

    @GetMapping("/shop/member/{id}")
    Member findById(@PathVariable("id") Long id);

    @GetMapping("/shop/member/findAll")
    List<Member> findAll();

    @GetMapping("/shop/member/findByName")
    List<Member> findByUsername(@RequestParam("username") String username);

    @PostMapping("/shop/member/findByUsernameAndPwd")
    Member findByUsernameAndPwd(@RequestBody Member member);

    @GetMapping("/shop/member/getReceiveAddressByMemberId")
    List<MemberReceiveAddress> getReceiveAddressByMemberId(@RequestParam("memberId") Long memberId);

    @GetMapping("/shop/member/getReceiveAddressByAddrId")
    MemberReceiveAddress getReceiveAddressByAddrId(@RequestParam("addressId") Long addressId);

}
