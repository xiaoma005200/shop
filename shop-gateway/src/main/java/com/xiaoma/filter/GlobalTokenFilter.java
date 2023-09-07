package com.xiaoma.filter;

import com.xiaoma.pojo.Member;
import com.xiaoma.utils.JWTTokenUtils;
import com.xiaoma.utils.TokenUtils;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Component
public class GlobalTokenFilter implements GlobalFilter, Ordered {
    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    JWTTokenUtils jwtTokenUtils;

    @Value("#{'${required.login.paths:}'.empty ? null : '${required.login.paths:}'.split(',') }")
    List<String> requiredLoginPaths;

    @Value("${jwt.secretKey}")
    String jwtSecretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.先获取相关的参数方便下面的操作
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String requestURL = request.getURI().toString();//当前的请求路径,http://localhost:xx/shop/xxx

        //2.定义一个存储必须登录的路径集合
        //List<String> requiredLoginPaths = Arrays.asList("/shop/order/index");

        //3.从cookie获取token进行判断
        String token = tokenUtils.getTokenFromCookie(request, "token");
        if (requestURL.contains("/login/") || requestURL.contains("/auth/")) {
           return chain.filter(exchange);//放行访问目标路径
        } else if (StringUtils.isNotBlank(token)) {
            Member member = new Gson().fromJson(jwtTokenUtils.parseToken(jwtSecretKey, token), Member.class);
            //String value = tokenUtils.getTokenFromRedis("user:" + token + ":token");
            String value = tokenUtils.getTokenFromRedis("user:" + member.getId() + ":token");
            if (StringUtils.isBlank(value)) {
                if (!CollectionUtils.isEmpty(requiredLoginPaths) && requiredLoginPaths.stream().anyMatch(requiredLoginPath -> requestURL.contains(requiredLoginPath))) {
                    //token失效或者被篡改,重定向到登录页面
                    response.setStatusCode(HttpStatus.FOUND);
                    try {
                        response.getHeaders().set(HttpHeaders.LOCATION, "/shop/login?returnURL=" + URLEncoder.encode(requestURL, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return response.setComplete();
                } else {
                    return chain.filter(exchange);
                }
            } else {
               //用户已经登录
                request.mutate().header("userId", member.getId()+"");
                request.mutate().header("username", member.getUsername());
                return chain.filter(exchange);
            }
        } else if (!CollectionUtils.isEmpty(requiredLoginPaths) && requiredLoginPaths.stream().anyMatch(requiredLoginPath -> requestURL.contains(requiredLoginPath))) {
            //token失效或者被篡改,重定向到登录页面
            response.setStatusCode(HttpStatus.FOUND);
            try {
                response.getHeaders().set(HttpHeaders.LOCATION,"/shop/login?returnURL="+ URLEncoder.encode(requestURL,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return response.setComplete();
        } else {//例如:购物路径,静态资源路径
            return chain.filter(exchange);
        }
    }

    /**
     * 代表过滤器的优先级,值越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
