package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopSearchCrumbs {
    /**
     * 属性名
     */
    private String attrName;

    /**
     * 属性值
     */
    private String valueName;

    /**
     * 每个面包屑导航上的链接拼接的参数
     * 最终参数=所有的参数-自身的valueId
     */
    private String urlParams;
}


