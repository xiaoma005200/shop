package com.xiaoma.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    * 库存单元图片表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuImage implements Serializable {
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
    @JsonProperty("spuImgId")
    private Long productImgId;

    /**
    * 是否默认
    */
    private String isDefault;

    private static final long serialVersionUID = 1L;
}