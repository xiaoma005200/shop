package com.xiaoma.controller;

import com.xiaoma.feign.MemberClient;
import com.xiaoma.pojo.Member;
import com.xiaoma.util.CookieUtils;
import com.xiaoma.util.JWTTokenUtils;
import com.xiaoma.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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

    @Autowired
    JWTTokenUtils jwtTokenUtils;

    @Value("${jwt.secretKey:}")
    String jwtSecretKey;

    /**
     * 用户认证
     * @param member 封装会员信息
     * @return 认证成功,重定向至index页面,认证失败返回login页面
     */
    @PostMapping("/auth")
    public String auth(Member member, HttpServletRequest request, HttpServletResponse response,String returnURL) throws UnsupportedEncodingException {
        // 1.去数据库校验用户名密码
        Member loginMember = memberClient.findByUsernameAndPwd(member);
        if (loginMember!=null) {// 用户名密码正确
            // 2.向浏览器写入一个包含token的cookie
            HashMap<String, Object> params = new HashMap<>();
            params.put("id",loginMember.getId());
            params.put("username",loginMember.getUsername());
            String jwtToken = jwtTokenUtils.generateJwtToken(UUID.randomUUID().toString(), "jwt_token", jwtSecretKey, params);
            cookieUtils.setCookie(request,response,"token",jwtToken,300,true);
            // 3.向redis写入一个token
            redisUtils.set("user:"+loginMember.getId()+":token",jwtToken,300, TimeUnit.SECONDS);
            // 如果有回调地址就重定向到回调地址,如果没有回调地址则重定向到首页
            if (StringUtils.isNotBlank(returnURL)) {
                return "redirect:"+ URLDecoder.decode(returnURL,"UTF-8");
            }else{
                return "redirect:http://localhost:8081/shop/index";// 登录页面,登录成功默认重定向到首页
            }
        }else{// 用户名密码不正确,返回登录页
            return "login";
        }
    }

    /**
     * 登录页
     * @return login.html
     */
    @GetMapping("/login")
    public String login(String returnURL,Model model) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(returnURL)) {
            model.addAttribute("returnURL", URLEncoder.encode(returnURL,"UTF-8"));
        }
        return "login";
    }

    /**
     * 首页
     * @return index.html
     */
    @GetMapping("/index")
    public String index(@RequestHeader Map<String,String> headers, HttpServletRequest request, Model model){
        /*// 1.从cookie中取得token
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
        return "/index";*/

        model.addAttribute("username",headers.get("username"));
        return "index";
    }

}
