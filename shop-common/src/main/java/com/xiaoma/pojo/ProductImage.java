package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 商品图片表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    /**
    * 编号
    */
    private Long id;

    /**
    * 商品id
    */
    private Long productId;

    /**
    * 图片名称
    */
    private String imgName;

    /**
    * 图片路径
    */
    private String imgUrl;
}