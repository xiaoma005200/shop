package com.xiaoma.service;

import com.xiaoma.exception.LockStockException;
import com.xiaoma.pojo.Order;
import com.xiaoma.vo.*;

import java.util.List;

public interface ProductOrderService {

    /**
     * 根据用户信息取出结算页需要的数据
     * @param userInfo
     * @return
     */
    OrderTrade getOrderTradeData(UserInfo userInfo);

    /**
     * 接收结算页面提交的订单相关信息,并生成订单
     * @param orderVO
     * @param userInfo
     * @return
     */
    ResponseData<Order> submitOrder(OrderVO orderVO, UserInfo userInfo) throws LockStockException;

    /**
     * 根据订单编号查询支付相关信息
     * @param orderSn
     * @return
     */
    PayVO getPayInfoByOrderSn(String orderSn);

    /**
     * 根据用户信息去查询用户关联的订单以及订单明细信息
     * @param userInfo
     * @return
     */
    List<Order> getOrderListByUser(UserInfo userInfo);

    /**
     * 根据订单的编号去修改订单的状态
     * @param orderSn
     * @param code
     */
    void updateOrderStatus(String orderSn, Integer code);


    /**
     *
     * @param orderSn  订单的唯一标识
     * @param channel  信道 手动确认消息使用
     * @param deliveryTag 消息的标识 手动确认消息使用
     */
    /*void autoReleaseOrder(String orderSn,
                          @Header(AmqpHeaders.CHANNEL) Channel channel
            , @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag);*/


    /**
     * 根据orderId查询订单
     * @param orderId
     * @return
     */
//    Order getOrderByOrderId(Long orderId);
}
