package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 属性表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAttrInfo {
    /**
    * 编号
    */
    private Long id;

    /**
    * 属性名称
    */
    private String attrName;

    private Long catalog3Id;

    /**
    * 启用：1 停用：0
    */
    private String isEnabled;
}