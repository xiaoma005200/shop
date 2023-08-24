package com.xiaoma.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sku平台属性值关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuAttrValue implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 属性id（冗余)
     */
    private Long attrId;

    /**
     * 属性值id
     */
    private Long valueId;

    /**
     * skuid
     */
    private Long skuId;

    private static final long serialVersionUID = 1L;
}