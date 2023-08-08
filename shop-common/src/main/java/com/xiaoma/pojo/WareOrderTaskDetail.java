package com.xiaoma.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WareOrderTaskDetail {
    /**
    * 编号
    */
    private Long id;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * sku名称
    */
    private String skuName;

    /**
    * 购买个数
    */
    private Integer skuNums;

    /**
    * 工作单编号
    */
    private Long taskId;

    /**
    * 仓库id
    */
    private Long wareId;

    /**
    * 1.已经锁定,2.解锁,3.库存扣减

    */
    private Integer lockStatus;
}