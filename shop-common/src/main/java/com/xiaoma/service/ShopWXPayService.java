package com.xiaoma.service;

import com.xiaoma.vo.PayVO;

import javax.servlet.http.HttpServletRequest;

public interface ShopWXPayService {
    /**
     * 调用微信官方的统一下单接口:https://api.mch.weixin.qq.com/pay/unifiedorder
     * @param orderSn
     * @return
     */
    PayVO unifiedOrder(String orderSn);


    /**
     * 处理异步通知,从request中获取微信发送的参数
     * @param request
     * @return
     */
    String asyncNotify(HttpServletRequest request);

    /**
     * 根据订单编号查询订单状态
     * @param orderSn
     * @return
     */
    PayVO getOrderStatus(String orderSn);
}
