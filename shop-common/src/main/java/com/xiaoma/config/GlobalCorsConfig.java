package com.xiaoma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域配置
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //对那些路径进行跨域访问
                        .allowedOrigins("*") //开放哪些ip,端口,域名
                        .allowCredentials(true) //是否允许发送cookie
                        .allowedMethods("GET","POST","PUT","DELETE"); //允许哪些请求方式
            }
        };
    }
}
