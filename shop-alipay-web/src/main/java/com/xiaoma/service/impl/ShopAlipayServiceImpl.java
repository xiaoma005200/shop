package com.xiaoma.service.impl;

import com.xiaoma.constant.OrderStatusEnum;
import com.xiaoma.feign.ShopOrderClient;
import com.xiaoma.mapper.generate.PaymentInfoMapper;
import com.xiaoma.pojo.PaymentInfo;
import com.xiaoma.service.ShopAlipayService;
import com.xiaoma.vo.AliPayAsyncNotifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ShopAlipayServiceImpl implements ShopAlipayService {
    @Autowired
    PaymentInfoMapper paymentInfoMapper;

    @Autowired
    ShopOrderClient shopOrderClient;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String updateInfoByPayResult(AliPayAsyncNotifyVo aliPayAsyncNotifyVo) {
        try {
            //1.向payment_info插入本次交易流水,方便商家将来对账
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setOrderSn(aliPayAsyncNotifyVo.getOut_trade_no());
            paymentInfo.setAlipayTradeNo(aliPayAsyncNotifyVo.getTrade_no());
            paymentInfo.setTotalAmount(new BigDecimal(aliPayAsyncNotifyVo.getTotal_amount()));
            paymentInfo.setSubject(aliPayAsyncNotifyVo.getSubject());
            paymentInfo.setPaymentStatus(aliPayAsyncNotifyVo.getTrade_status());
            paymentInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aliPayAsyncNotifyVo.getGmt_create()));
            paymentInfo.setCallbackTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aliPayAsyncNotifyVo.getNotify_time()));
            //.........................根据需要设置其它参数
            paymentInfoMapper.insertSelective(paymentInfo);
            //2.修改订单状态
           /* if (支付成功) {
                //将订单状态修改为已支付
            }*/
            if (aliPayAsyncNotifyVo.getTrade_status().equalsIgnoreCase("TRADE_SUCCESS")
                    || aliPayAsyncNotifyVo.getTrade_status().equalsIgnoreCase("TRADE_FINISHED")) {
                System.out.println("修改订单状态");
                //调用订单模块去修改订单状态
                shopOrderClient.updateOrderStatus(aliPayAsyncNotifyVo.getOut_trade_no(), OrderStatusEnum.PAYED.getCode());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}
