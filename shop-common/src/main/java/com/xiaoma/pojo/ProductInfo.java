package com.xiaoma.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo implements Serializable {
    /**
    * 商品id
    */
    private Long id;

    /**
    * 商品名称
    */
    @JsonProperty("spuName")
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

    /**
     * 接收页面提交的图片(新增)
     */
    @JsonProperty("spuImageList")
    List<ProductImage> productImageList;

    /**
     * 存储SPU对应的销售属性(新增)
     */
    @JsonProperty("spuSaleAttrList")
    List<ProductSaleAttr> productSaleAttrList;

    /**
     * 品牌(新增)
     */
    private Brand brand;

    private static final long serialVersionUID = 1L;
}