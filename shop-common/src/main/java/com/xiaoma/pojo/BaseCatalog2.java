package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCatalog2 {
    /**
    * 编号
    */
    private Integer id;

    /**
    * 二级分类名称
    */
    private String name;

    /**
    * 一级分类编号
    */
    private Integer catalog1Id;
}