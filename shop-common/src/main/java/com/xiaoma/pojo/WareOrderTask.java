package com.xiaoma.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 库存工作单表 库存工作单表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WareOrderTask {
    /**
    * 编号
    */
    private Long id;

    private String orderSn;

    /**
    * 订单编号
    */
    private Long orderId;

    /**
    * 收货人
    */
    private String consignee;

    /**
    * 收货人电话
    */
    private String consigneeTel;

    /**
    * 送货地址
    */
    private String deliveryAddress;

    /**
    * 订单备注
    */
    private String orderComment;

    /**
    * 付款方式 1:在线付款 2:货到付款
    */
    private String paymentWay;

    private String taskStatus;

    /**
    * 订单描述
    */
    private String orderBody;

    /**
    * 物流单号
    */
    private String trackingNo;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 仓库编号
    */
    private Long wareId;

    /**
    * 工作单备注
    */
    private String taskComment;
}