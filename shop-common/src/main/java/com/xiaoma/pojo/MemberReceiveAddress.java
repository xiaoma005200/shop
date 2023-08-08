package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 会员收货地址表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReceiveAddress {
    private Long id;

    private Long memberId;

    /**
    * 收货人名称
    */
    private String name;

    private String phoneNumber;

    /**
    * 是否为默认
    */
    private Integer defaultStatus;

    /**
    * 邮政编码
    */
    private String postCode;

    /**
    * 省份/直辖市
    */
    private String province;

    /**
    * 城市
    */
    private String city;

    /**
    * 区
    */
    private String region;

    /**
    * 详细地址(街道)
    */
    private String detailAddress;
}