package com.xiaoma.service.impl;

import com.xiaoma.config.WXPayConfig;
import com.xiaoma.constant.OrderStatusEnum;
import com.xiaoma.feign.ShopOrderClient;
import com.xiaoma.service.ShopWXPayService;
import com.xiaoma.util.WXPayConstants;
import com.xiaoma.util.WXPayRequest;
import com.xiaoma.util.WXPayUtil;
import com.xiaoma.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShopWXPayServiceImpl implements ShopWXPayService {

    @Autowired
    ShopOrderClient shopOrderClient;

    @Autowired
    WXPayConfig wxPayConfig;

    @Override
    public PayVO unifiedOrder(String orderSn) {
        //1.根据orderSn获取最新的订单信息
        PayVO payVO = shopOrderClient.getPayInfoByOrderSn(orderSn);

        //2.调用微信的统一下单接口
        //2.1构造请求参数
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", wxPayConfig.getAppId());
        params.put("mch_id", wxPayConfig.getMchId());
        params.put("nonce_str", WXPayUtil.generateNonceStr());
        params.put("body", payVO.getSubject());
        params.put("out_trade_no", payVO.getOrderSn());
        //params.put("total_fee", payVO.getPayAmount().multiply(new BigDecimal("100")).toString());//真实的价格
        params.put("total_fee", "1");//代表1分钱
        params.put("spbill_create_ip", "192.168.1.168");
        params.put("notify_url", wxPayConfig.getNotify());
        params.put("trade_type", "NATIVE");

        //2.2.将参数进行签名
        try {
            String paramsXML = WXPayUtil.generateSignedXml(params, wxPayConfig.getMchKey());

            //2.3.利用拼装好的xml参数进行统一下单
            WXPayRequest wxPayRequest = new WXPayRequest();
            String respXML = wxPayRequest.requestOnce("https://api.mch.weixin.qq.com/pay/unifiedorder", paramsXML);

            //2.4.获取到响应的参数
            Map<String, String> respMap = WXPayUtil.xmlToMap(respXML);
            if (respMap.get("return_code").equals("SUCCESS") &&
                    respMap.get("result_code").equals("SUCCESS")) {
                System.out.println(respMap.get("code_url"));
                payVO.setCodeUrl(respMap.get("code_url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payVO;
    }

    @Override
    public String asyncNotify(HttpServletRequest request) {
        HashMap<String, String> respMap = new HashMap<>();//存放响应给微信的参数
        try {
            //1.将请求的xml字符串逐行读取,存入到StringBuilder中
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            //2.将请求的xml字符串转换成map
            Map<String, String> reqMap = WXPayUtil.xmlToMap(sb.toString());
            if (reqMap.get("return_code").equals(WXPayConstants.SUCCESS)) {
                //验签:验证传递过来参数是否被篡改
                if (WXPayUtil.isSignatureValid(reqMap, wxPayConfig.getMchKey())) {
                    //验签成功,修改订单状态
                    System.out.println("微信异步通知修改订单状态"+reqMap.get("out_trade_no"));
                    shopOrderClient.updateOrderStatus(reqMap.get("out_trade_no"), OrderStatusEnum.PAYED.getCode());

                    respMap.put("return_code", WXPayConstants.SUCCESS);
                    respMap.put("return_msg", "OK");
                } else {
                    //验签失败
                    respMap.put("return_code", WXPayConstants.FAIL);
                    respMap.put("return_msg", "验签失败");
                }
            } else {
                respMap.put("return_code", WXPayConstants.FAIL);
                respMap.put("return_msg", "支付失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3.将响应参数的map转换成xml发送给微信服务器
        String result = null;
        try {
            result = WXPayUtil.mapToXml(respMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public PayVO getOrderStatus(String orderSn) {
        return shopOrderClient.getPayInfoByOrderSn(orderSn);
    }
}
