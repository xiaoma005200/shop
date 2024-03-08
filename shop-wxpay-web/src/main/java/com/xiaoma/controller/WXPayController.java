package com.xiaoma.controller;

import com.xiaoma.service.ShopWXPayService;
import com.xiaoma.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop/wxPay")
public class WXPayController {

    @Autowired
    ShopWXPayService shopWXPayService;

    @GetMapping("/page")
    @ResponseBody
    public String index(String orderSn, Model model){
        PayVO payVO = shopWXPayService.unifiedOrder(orderSn);
        model.addAttribute("wxPayVO",payVO);
        return "page";
    }

    @RequestMapping("/notify")
    @ResponseBody
    public String notify(HttpServletRequest request){
        return shopWXPayService.asyncNotify(request);
    }

    @PostMapping("/check/order")
    @ResponseBody
    public PayVO getOrderStatus(String orderSn){
        return shopWXPayService.getOrderStatus(orderSn);
    }
}
