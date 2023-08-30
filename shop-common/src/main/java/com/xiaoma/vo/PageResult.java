package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 定义将来返回给页面的数据
 * 将来页面用到什么数据,就在PageResult中定义相关的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    /**
     * 总条数
     */
    private long totalCount;


    /**
     * 总页数
     */
    private long totalPage;


    /**
     * 封装页面的数据
     */
    private List<T> pageData;


    /**
     * 存储聚合查询出来的valueId
     */
    private List<Integer> valueIds;
}
