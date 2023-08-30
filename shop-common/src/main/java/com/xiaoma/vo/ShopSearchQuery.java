package com.xiaoma.vo;

import com.xiaoma.pojo.SearchSkuInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装页面搜索相关的参数
 * 根据需求不断的往ShopSearchQuery中定义相关参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopSearchQuery extends SearchSkuInfo {

    /**
     * 页面搜索关键字
     */
    private String keyword;


}
