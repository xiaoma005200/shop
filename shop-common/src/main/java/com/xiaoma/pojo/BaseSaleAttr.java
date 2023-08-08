package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseSaleAttr {
    /**
    * 编号
    */
    private Long id;

    /**
    * 销售属性名称
    */
    private String name;
}