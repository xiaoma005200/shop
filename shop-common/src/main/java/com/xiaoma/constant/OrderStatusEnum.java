package com.xiaoma.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  OrderStatusEnum {
    CREATE_NEW(1000,"待付款"),
    PAYED(1001,"已付款"),
    SENDED(1002,"已发货"),
    RECIEVED(1003,"已完成"),
    CANCLED(1004,"已取消"),
    SERVICING(1005,"售后中"),
    SERVICED(1006,"售后完成"),
    INVALID(1007,"无效订单"),
    LOCKED_STOCK_FAIL(1008,"订单锁定库存失败"),
    LOCKED_STOCK_SUCCESS(1009,"订单锁定库存成功");

    private Integer code;
    private String msg;
}
