package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    * sku销售属性值
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuSaleAttrValue implements Serializable {
    /**
    * id
    */
    private Long id;

    /**
    * 库存单元id
    */
    private Long skuId;

    /**
    * 销售属性id（冗余)
    */
    private Long saleAttrId;

    /**
    * 销售属性名称(冗余)
    */
    private String saleAttrName;

    /**
    * 销售属性值id
    */
    private Long saleAttrValueId;

    /**
    * 销售属性值名称(冗余)
    */
    private String saleAttrValueName;
}