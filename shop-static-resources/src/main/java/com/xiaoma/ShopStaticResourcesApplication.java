package com.xiaoma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShopStaticResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopStaticResourcesApplication.class, args);
    }

}
