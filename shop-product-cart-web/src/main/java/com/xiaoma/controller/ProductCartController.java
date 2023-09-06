package com.xiaoma.controller;

import com.xiaoma.util.CookieUtils;
import com.xiaoma.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/shop/cart")
public class ProductCartController {

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 访问购物车首页
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
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
        model.addAttribute("returnURL", URLEncoder.encode(request.getRequestURL().toString(),"UTF-8"));
        return "/index";
    }
}
