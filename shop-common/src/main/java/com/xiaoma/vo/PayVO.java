package com.xiaoma.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayVO {
    /*订单编号:必须设置*/
    private String orderSn;

    /*支付金额:必须设置*/
    private BigDecimal payAmount;

    /*订单名称:必须设置*/
    private String subject;

    /*订单描述:可为空*/
    private String body;

    /*封装微信的支付二维码链接*/
    private String codeUrl;

    /*封装订单的状态*/
    private Integer orderStatus;
    //.....
}
