package com.xiaoma.controller;

import com.xiaoma.constant.OrderStatusEnum;
import com.xiaoma.exception.LockStockException;
import com.xiaoma.pojo.Order;
import com.xiaoma.service.ProductOrderService;
import com.xiaoma.util.CookieUtils;
import com.xiaoma.util.RedisUtils;
import com.xiaoma.util.ResultUtils;
import com.xiaoma.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/order")
public class ProductOrderController {

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    ProductOrderService productOrderService;

    /**
     * 结算页面
     * @return
     */
    @GetMapping("/trade")
    public String trade(HttpServletRequest request,Model model){
        /*String userId = request.getHeader("userId");
        String username = request.getHeader("username");
        String userKey = cookieUtils.getCookieValue(request, "user-key", true);
        UserInfo userInfo = new UserInfo(userId, username, userKey);*/
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");

        OrderTrade orderTrade = productOrderService.getOrderTradeData(userInfo);

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("orderTrade",orderTrade);
        return  "trade";
    }

    /**
     * 支付页面
     * @return
     */
    @GetMapping("/pay")
    public String pay(HttpServletRequest request,Model model,String orderSn){
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        PayVO payVO = productOrderService.getPayInfoByOrderSn(orderSn);

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("payVO",payVO);
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
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        List<Order> orderList = productOrderService.getOrderListByUser(userInfo);
        model.addAttribute("orderList", orderList);
        return "index";
    }

    @PostMapping("/submitOrder")
    @ResponseBody
    public ResponseData<Order> submitOrder(OrderVO orderVO, HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        try {
            return productOrderService.submitOrder(orderVO,userInfo);
        } catch (LockStockException e) {
            e.printStackTrace();
            return ResultUtils.result(OrderStatusEnum.LOCKED_STOCK_FAIL,null);
        }
    }

    @GetMapping("/getPayInfoByOrderSn")
    @ResponseBody /*返回json数据*/
    public PayVO getPayInfoByOrderSn(String orderSn){
        return productOrderService.getPayInfoByOrderSn(orderSn);
    }

    @GetMapping("/updateOrderStatus")
    @ResponseBody
    public void updateOrderStatus(String orderSn,Integer code){
        productOrderService.updateOrderStatus(orderSn,code);
    }
}
