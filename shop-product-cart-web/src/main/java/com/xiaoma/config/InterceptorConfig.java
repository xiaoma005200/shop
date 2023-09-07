package com.xiaoma.config;

import com.xiaoma.interceptor.CartInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 例如:访问 localhost:8081/shop/cart/index --> GlobalTokenFilter -放行--> shop-product-cart-web
 *          --->访问CartInterceptor---放行-> Controller中(shop/cart/index)
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    CartInterceptor cartInterceptor;

    /*注册拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cartInterceptor).addPathPatterns("/shop/cart/**");
    }
}

