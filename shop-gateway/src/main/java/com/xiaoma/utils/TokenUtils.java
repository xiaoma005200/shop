package com.xiaoma.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {
    @Autowired
    RedisUtils redisUtils;

    /*从cookie中取token*/
    public String getTokenFromCookie(ServerHttpRequest request, String key) {
        HttpCookie httpCookie = request.getCookies().getFirst(key);
        if (httpCookie !=null){
            return httpCookie.getValue();
        }
        return null;
    }

    /*从redis取token*/
    public String getTokenFromRedis(String key) {
        Object value = redisUtils.get(key);
        return value == null ? null : (String) value;
    }
}