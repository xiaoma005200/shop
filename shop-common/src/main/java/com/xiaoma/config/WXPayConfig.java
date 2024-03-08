package com.xiaoma.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "wxpay")
@Component
@Data
public class WXPayConfig {
    /*服务号or公众号appId*/
    private String appId;

    /*商户mch_id*/
    private String mchId;

    /*API密钥*/
    private String mchKey;

    /*异步通知地址*/
    private String notify;
}
