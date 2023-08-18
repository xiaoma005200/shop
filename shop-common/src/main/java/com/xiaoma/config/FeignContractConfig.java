package com.xiaoma.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 解决openfeign不支持多接口和多级接口继承问题配置类
 */
@Configuration
public class FeignContractConfig {

    @Bean
    public Contract feignContract() {
        return new HierarchicalContract();
    }
}