package com.xiaoma.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 商品评价表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;

    private Long productId;

    private String memberNickName;

    private String productName;

    /**
    * 评价星数：0->5
    */
    private Integer star;

    /**
    * 评价的ip
    */
    private String memberIp;

    private Date createTime;

    private Integer showStatus;

    /**
    * 购买时的商品属性
    */
    private String productAttribute;

    private Integer collectCouont;

    private Integer readCount;

    private String content;

    /**
    * 上传图片地址，以逗号隔开
    */
    private String pics;

    /**
    * 评论用户头像
    */
    private String memberIcon;

    private Integer replayCount;
}