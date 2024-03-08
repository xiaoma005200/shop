package com.xiaoma.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String app_id;

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchant_private_key;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipay_public_key;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付完成的时候,由阿里的服务器主动调用，一般情况下，25小时以内完成8次通知（通知的间隔频率一般是:4m , 10m , 10m , 1h, 2h , 6h , 15h ) ;
    public String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付成功后，浏览器跳转的商户页面
    public String return_url;

    // 签名方式
    public String sign_type;

    // 字符编码格式
    public String charset;

    // 支付宝网关
    public String gatewayUrl;

    // 支付宝网关日志
    public String log_path;

}
