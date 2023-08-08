package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 库存单元图片表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuImage {
    /**
    * 编号
    */
    private Long id;

    /**
    * 商品id
    */
    private Long skuId;

    /**
    * 图片名称（冗余）
    */
    private String imgName;

    /**
    * 图片路径(冗余)
    */
    private String imgUrl;

    /**
    * 商品图片id
    */
    private Long productImgId;

    /**
    * 是否默认
    */
    private String isDefault;
}