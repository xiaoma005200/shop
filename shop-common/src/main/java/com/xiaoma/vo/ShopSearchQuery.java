package com.xiaoma.vo;

import com.xiaoma.pojo.SearchSkuInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    /**
     * 接收页面点击的页码
     */
    private int currentPage=1;


    /**
     * 每页显示的条数
     */
    private int pageSize = 8;


    /**
     * 定义一个专门接收页面valueId=xx&valueId=xx,...的集合
     */
    private List<String> valueId;


}
