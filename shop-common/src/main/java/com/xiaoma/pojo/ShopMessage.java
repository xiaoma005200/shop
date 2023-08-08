package com.xiaoma.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopMessage {
    private Integer messagaeId;

    /**
    * 存储json格式
    */
    private String messageContent;

    private String exchangeName;

    private String messageRoutingKey;

    private String exchangeRoutingKey;

    /**
    * 0-新建 1-已发送 3-已抵达 4-已消费
    */
    private Integer messageStatus;

    private Date createTime;

    private Date updateTime;
}