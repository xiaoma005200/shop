package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 一级分类表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCatalog1 {
    /**
    * 编号
    */
    private Integer id;

    /**
    * 分类名称
    */
    private String name;
}