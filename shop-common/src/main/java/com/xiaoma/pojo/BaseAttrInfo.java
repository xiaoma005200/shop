package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
    * 属性表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAttrInfo implements Serializable {
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

    /**
     * 新建一个集合接收属性属性值列表
     */
    private List<BaseAttrValue> attrValueList;

    private static final long serialVersionUID = 1L;

}