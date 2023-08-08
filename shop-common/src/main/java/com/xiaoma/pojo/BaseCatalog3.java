package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCatalog3 {
    /**
    * 编号
    */
    private Long id;

    /**
    * 三级分类名称
    */
    private String name;

    /**
    * 二级分类编号
    */
    private Long catalog2Id;
}