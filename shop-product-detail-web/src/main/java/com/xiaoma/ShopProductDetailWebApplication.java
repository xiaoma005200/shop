package com.xiaoma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient  //允许到注册中心拉取服务
@EnableFeignClients  //调用启用feign
public class ShopProductDetailWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopProductDetailWebApplication.class, args);
    }

}
