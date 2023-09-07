package com.xiaoma.controller;

import com.xiaoma.util.CookieUtils;
import com.xiaoma.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("/shop/cart")
public class ProductCartController {

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    RedisUtils redisUtils;

    @PostMapping("/addToCart")
    public String addToCart() {
        return "success";
    }

    /**
     * 访问购物车首页
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
