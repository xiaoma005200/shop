package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    * spu销售属性值
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleAttrValue implements Serializable {
    /**
    * id
    */
    private Long id;

    /**
    * 商品id
    */
    private Long productId;

    /**
    * 销售属性id
    */
    private Long saleAttrId;

    /**
    * 销售属性值名称
    */
    private String saleAttrValueName;

    /**
     * 存储sku的选中状态，1代表被选中，0代表不被选中
     */
    private Integer isChecked;

    private static final long serialVersionUID = 1L;
}