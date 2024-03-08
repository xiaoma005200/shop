package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    /*根据addressId去数据库查询最新的收货地址*/
    private Long addressId;

    /*支付方式*/

    /*发票信息*/

    /*优惠信息*/

    /*没必要接收结算页的购物项，直接重新获取购物车选中项更准确*/

    /*防重令牌*/
    private String orderToken;
}
