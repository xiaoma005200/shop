package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 公司收发货地址表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAddress {
    private Long id;

    /**
    * 地址名称
    */
    private String addressName;

    /**
    * 默认发货地址：0->否；1->是
    */
    private Integer sendStatus;

    /**
    * 是否默认收货地址：0->否；1->是
    */
    private Integer receiveStatus;

    /**
    * 收发货人姓名
    */
    private String name;

    /**
    * 收货人电话
    */
    private String phone;

    /**
    * 省/直辖市
    */
    private String province;

    /**
    * 市
    */
    private String city;

    /**
    * 区
    */
    private String region;

    /**
    * 详细地址
    */
    private String detailAddress;
}