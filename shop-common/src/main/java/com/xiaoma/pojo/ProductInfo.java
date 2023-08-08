package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    /**
    * 商品id
    */
    private Long id;

    /**
    * 商品名称
    */
    private String productName;

    /**
    * 商品描述(后台简述）
    */
    private String description;

    /**
    * 三级分类id
    */
    private Long catalog3Id;

    /**
    * 品牌id
    */
    private Long tmId;
}