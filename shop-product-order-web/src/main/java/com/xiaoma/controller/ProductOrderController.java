package com.xiaoma.controller;

import com.xiaoma.util.CookieUtils;
import com.xiaoma.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("/shop/order")
public class ProductOrderController {

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    RedisUtils redisUtils;

    @GetMapping("/trade")
    public String trade(){
        return  "trade";
    }

    @GetMapping("/pay")
    public String pay(){
        return  "pay";
    }

    /**
     * 访问订单首页,必须登录才能访问订单页面
     * @param request
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/index")
    public String index(@RequestHeader Map<String,String> headers, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        /*// 1.从cookie中取得token
        String userId = cookieUtils.getCookieValue(request, "token", true);

        // 2.根据token中的保存的userId判断
        if (StringUtils.isNotBlank(userId)) {
            // 3.根据userId去redis中获取token
            Object value = redisUtils.get("user:" + userId + ":token");
            if (value!=null) {
                // 说明用户已经登录,将用户信息添加到model域中
                model.addAttribute("username",value);
                return "/index"; // 已登录,前往订单页面
            }
        }
        // 重定向到登录页面(未登录/redis缓存过期)同时使用returnURL记录一个回调地址,实现登录后跳转
        return "redirect:http://localhost:9004/shop/login?returnURL=" + URLEncoder.encode(request.getRequestURL().toString(),"UTF-8");*/

        model.addAttribute("username",headers.get("username"));
        return "/index"; // 找网关
    }
}
