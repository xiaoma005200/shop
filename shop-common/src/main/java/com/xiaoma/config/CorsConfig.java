//package com.xiaoma.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")   //所有方法都做处理跨域
//                //.allowedOrigins("http://localhost:80")  //允许跨域的请求头
//                .allowedOrigins("*")  //允许跨域的请求头
//                .allowedMethods("*")  //润许通过地请求方法
//                .allowedHeaders("*");  //允许的请求头
//    }
//}
