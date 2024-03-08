package com.xiaoma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.xiaoma.mapper")
public class ShopWxpayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopWxpayWebApplication.class, args);
    }

}
