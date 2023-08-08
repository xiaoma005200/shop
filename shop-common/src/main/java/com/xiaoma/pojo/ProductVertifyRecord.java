package com.xiaoma.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 商品审核记录
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVertifyRecord {
    private Long id;

    private Long productId;

    private Date createTime;

    /**
    * 审核人
    */
    private String vertifyMan;

    private Integer status;

    /**
    * 反馈详情
    */
    private String detail;
}