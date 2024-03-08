package com.xiaoma.vo;

import lombok.Data;

/**
 * 封装支付宝异步通知的VO
 */
@Data
public class AliPayAsyncNotifyVo {
    /**
     * 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmt_create;

    /**
     * 编码格式
     */
    private String charset;

    /**
     * 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private String gmt_payment;
    /**
     * 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private String notify_time;

    /**
     * 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来
     */
    private String subject;

    /**
     * 异步返回结果的签名
     */
    private String sign;

    /**
     * 买家支付宝账号对应的支付宝唯一用户号。以 2088 开头的纯 16 位数字
     * 例如:2088102122524333
     */
    private String buyer_id;

    /**
     * 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来
     */
    private String body;

    /**
     * 用户在交易中支付的可开发票的金额，单位为元，精确到小数点后2位
     */
    private String invoice_amount;

    /**
     * 调用的接口版本，固定为：1.0
     */
    private String version;

    /**
     * 通知校验ID
     */
    private String notify_id;

    /**
     * 支付成功的各个渠道金额信息，详见下文 资金明细信息说明
     * [{“amount”:“15.00”,“fundChannel”:“ALIPAYACCOUNT”}]
     */
    private String fund_bill_list;

    /**
     * 通知的类型
     */
    private String notify_type;

    /**
     * 原支付请求的商户订单号,电商系统中生成的orderSn
     */
    private String out_trade_no;

    /**
     * 本次交易支付的订单金额，单位为人民币（元），精确到小数点后2位
     */
    private String total_amount;

    /**
     * 交易目前所处的状态，见下文 交易状态说明
     */
    private String trade_status;

    /**
     * 支付宝交易凭证号,可以在支付宝中查询到
     * 2013112011001004330000121536
     */
    private String trade_no;

    /**
     * 授权方的appid，由于本接口暂不开放第三方应用授权，因此auth_app_id=app_id
     */
    private String auth_app_id;

    /**
     * 商家在交易中实际收到的款项，单位为元，精确到小数点后2位
     */
    private String receipt_amount;

    /**
     * 使用集分宝支付的金额，单位为元，精确到小数点后2位
     */
    private String point_amount;

    /**
     * 支付宝分配给开发者的应用 ID
     */
    private String app_id;

    /**
     * 用户在交易中支付的金额，单位为元，精确到小数点后2位
     */
    private String buyer_pay_amount;

    /**
     * 签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     * 例如:RSA2
     */
    private String sign_type;

    /**
     * 卖家支付宝用户号
     */
    private String seller_id;

}