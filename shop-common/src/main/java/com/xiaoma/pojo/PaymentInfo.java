package com.xiaoma.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 支付信息表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
    /**
    * 编号
    */
    private Long id;

    /**
    * 对外业务编号
    */
    private String orderSn;

    /**
    * 订单编号
    */
    private String orderId;

    /**
    * 支付宝交易编号
    */
    private String alipayTradeNo;

    /**
    * 支付金额
    */
    private BigDecimal totalAmount;

    /**
    * 交易内容
    */
    private String subject;

    /**
    * 支付状态
    */
    private String paymentStatus;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建时间
    */
    private Date confirmTime;

    /**
    * 回调信息
    */
    private String callbackContent;

    private Date callbackTime;
}