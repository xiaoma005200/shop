package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 属性值表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAttrValue {
    /**
    * 编号
    */
    private Long id;

    /**
    * 属性值名称
    */
    private String valueName;

    /**
    * 属性id
    */
    private Long attrId;

    /**
    * 启用：1 停用：0 1
    */
    private String isEnabled;
}