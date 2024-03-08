package com.xiaoma.controller;

import com.xiaoma.feign.ShopOrderClient;
import com.xiaoma.service.ShopAlipayService;
import com.xiaoma.util.AlipayUtils;
import com.xiaoma.vo.AliPayAsyncNotifyVo;
import com.xiaoma.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop/alipay")
public class ShopAlipayController {

    @Autowired
    ShopOrderClient shopOrderClient;

    @Autowired
    AlipayUtils alipayUtils;

    @Autowired
    ShopAlipayService shopAlipayService;

    /*支付宝支付页面*/
    @GetMapping("/page")
    @ResponseBody
    public String page(String orderSn){
        // 1.根据orderSn查询订单的最新信息，将需要的订单参数封装到payVO
        PayVO payVO = shopOrderClient.getPayInfoByOrderSn(orderSn);
        // 2.根据payVO中封装的参数去构造对接支付宝支付需要的业务参数
        String result = alipayUtils.getPayPage(payVO);
        System.out.println("===================这是调用page方法返回的结果开始===================");
        System.out.println(result);
        System.out.println("===================这是调用page方法返回的结果结束===================");
        return result;
    }

    @PostMapping("/notify")
    @ResponseBody
    public String alipayNotify(AliPayAsyncNotifyVo aliPayAsyncNotifyVo, HttpServletRequest request) {
        System.out.println("===================alipayNotify支付宝异步回调开始===================");
        System.out.println("aliPayAsyncNotifyVo:" + aliPayAsyncNotifyVo);
        System.out.println("request:" + request);
        System.out.println("trade_status:" + request.getParameter("trade_status"));
        System.out.println("验签结果：" + alipayUtils.alipayRSACheckV1(request));

        if (alipayUtils.alipayRSACheckV1(request)) {
            System.out.println("验签成功");
            return shopAlipayService.updateInfoByPayResult(aliPayAsyncNotifyVo);
        } else {
            System.out.println("验签失败");
            return "fail";
        }
    }

}
