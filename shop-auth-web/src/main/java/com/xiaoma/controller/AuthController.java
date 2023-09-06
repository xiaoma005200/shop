package com.xiaoma.controller;

import com.xiaoma.feign.MemberClient;
import com.xiaoma.pojo.Member;
import com.xiaoma.util.CookieUtils;
import com.xiaoma.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/shop")
public class AuthController {

    @Autowired
    MemberClient memberClient;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 用户认证
     * @param member 封装会员信息
     * @return 认证成功,重定向至index页面,认证失败返回login页面
     */
    @PostMapping("/auth")
    public String auth(Member member, HttpServletRequest request, HttpServletResponse response){
        // 1.去数据库校验用户名密码
        Member loginMember = memberClient.findByUsernameAndPwd(member);
        if (loginMember!=null) {
            // 用户名密码正确
            // 2.向浏览器写入一个包含token的cookie
            cookieUtils.setCookie(request,response,"token",loginMember.getId()+"",300,true);
            // 3.向redis写入一个token
            redisUtils.set("user:"+loginMember.getId()+":token",loginMember.getUsername(),300, TimeUnit.SECONDS);
            return "redirect:/shop/index";
        }else{
            // 用户名密码不正确
            return "/login";
        }
    }

    /**
     * 登录页
     * @return login.html
     */
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     * 首页
     * @return index.html
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model){
        // 1.从cookie中取得token
        String userId = cookieUtils.getCookieValue(request, "token", true);

        // 2.根据token中的保存的userId判断
        if (StringUtils.isNotBlank(userId)) {
            // 3.根据userId去redis中获取token
            Object value = redisUtils.get("user:" + userId + ":token");
            if (value!=null) {
                // 说明用户已经登录,将用户信息添加到model域中
                model.addAttribute("username",value);
            }
        }
        return "/index";
    }


}
