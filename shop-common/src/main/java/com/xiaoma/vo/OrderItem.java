package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    /*商品id*/
    private Long skuId;

    /*购买数量*/
    private Integer number;

    /*订单的唯一编号*/
    private String orderSn;

    /*订单的id*/
    private Long orderId;
}
