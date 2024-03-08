package com.xiaoma.config;

import com.xiaoma.interceptor.OrderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 例如:访问 localhost:8081/order/cart/index --> GlobalTokenFilter -放行--> shop-product-order-web
 *          --->访问OrderInterceptor---放行-> Controller中(shop/order/index)
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    OrderInterceptor orderInterceptor;

    /*注册拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(orderInterceptor).addPathPatterns("/shop/order/**");
    }
}

