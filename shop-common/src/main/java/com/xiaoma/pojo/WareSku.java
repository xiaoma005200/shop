package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WareSku {
    /**
    * 编号
    */
    private Long id;

    /**
    * skuid
    */
    private Long skuId;

    /**
    * 仓库id
    */
    private Long warehouseId;

    /**
    * 库存数
    */
    private Integer stock;

    /**
    * 存货名称
    */
    private String stockName;

    private Integer stockLocked;
}