package com.xiaoma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.xiaoma.mapper")
public class ShopUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUserProviderApplication.class, args);
    }

}
