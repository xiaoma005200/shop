package com.xiaoma.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存单元表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuInfo implements Serializable {
    /**
     * 库存id(itemID)
     */
    private Long id;

    /**
     * 商品id
     */
    @JsonProperty("spuId")
    private Long productId;

    /**
     * 价格
     */
    private Double price;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 商品规格描述
     */
    private String skuDesc;

    private Double weight;

    /**
     * 品牌(冗余)
     */
    private Long tmId;

    /**
     * 三级分类id（冗余)
     */
    private Long catalog3Id;

    /**
     * 默认显示图片(冗余)
     */
    private String skuDefaultImg;

    /**
     * 封装SKU对应的基本属性和属性值
     */
    private List<SkuAttrValue> skuAttrValueList;

    /**
     * 封装SKU对应的销售属性和销售属性值
     */
    private List<SkuSaleAttrValue> skuSaleAttrValueList;


    /**
     * 封装SKU对应的图片信息
     */

    private List<SkuImage> skuImageList;

    private static final long serialVersionUID = 1L;
}