package com.xiaoma.controller;

import com.xiaoma.service.ProductCartService;
import com.xiaoma.util.CookieUtils;
import com.xiaoma.util.RedisUtils;
import com.xiaoma.vo.Cart;
import com.xiaoma.vo.CartItem;
import com.xiaoma.vo.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/cart")
public class ProductCartController {

    @Autowired
    ProductCartService productCartService;

    @PostMapping("/addToCart")
    public String addToCart(HttpServletRequest request,Long skuId,Integer num,Model model) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        CartItem cartItem = productCartService.addToCart(userInfo, skuId, num);
        model.addAttribute("cartItem",cartItem);
        return "success";
    }

    /**
     * 访问购物车首页
     * @return
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request,Model model) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        Cart cart = productCartService.getUserCart(userInfo);
        model.addAttribute("cart",cart);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("returnURL","http://localhost:8081" + request.getRequestURI());
        return "index";
    }

    @GetMapping("checkCartItem")
    public String checkCartItem(HttpServletRequest request,Long skuId,Integer check) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        productCartService.checkCartItem(userInfo,skuId,check);
        return "redirect:http://localhost:8081/shop/cart/index";
    }

    @GetMapping("changeNumber")
    public String changeNumber(HttpServletRequest request,Long skuId,Integer number) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        productCartService.changeCartItemNumber(userInfo,skuId,number);
        return "redirect:http://localhost:8081/shop/cart/index";
    }

    @GetMapping("deleteCartItem")
    public String changeNumber(HttpServletRequest request,Long skuId) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        productCartService.deleteCartItemBySkuId(userInfo,skuId);
        return "redirect:http://localhost:8081/shop/cart/index";
    }


    /*@PostMapping("getCheckedCartItems")
    @ResponseBody
    public List<CartItem> getCheckedCartItems(@RequestBody UserInfo userInfo) {
        return productCartService.getCheckedCartItems(userInfo);
    }*/

}
