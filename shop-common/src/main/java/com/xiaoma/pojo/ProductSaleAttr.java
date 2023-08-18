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
public class ProductSaleAttr implements Serializable {
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
    * 销售属性名称(冗余)
    */
    private String saleAttrName;

    /**
     * 商品销售属性值列表
     */
    @JsonProperty("spuSaleAttrValueList")
    private List<ProductSaleAttrValue> productSaleAttrValueList;

    private static final long serialVersionUID = 1L;
}