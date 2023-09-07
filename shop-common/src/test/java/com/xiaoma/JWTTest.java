package com.xiaoma;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTTest {
    @Test
    public void generateJWTToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", "1");
        params.put("username", "张三");
        JwtBuilder jwtBuilder = Jwts.builder().setId(UUID.randomUUID().toString()) // 设置编号
                .setSubject("testJWT") // 设置主题
                .setIssuedAt(new Date()) // 设置签发时间
                .setClaims(params) // 令牌中含有的参数
                .signWith(SignatureAlgorithm.HS256, "shop");// 选择加密算法,指定密钥
        System.out.println(jwtBuilder.compact());
    }

    @Test
    public void parseJWTToken() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxIiwidXNlcm5hbWUiOiLlvKDkuIkifQ.k2DNB1_M9NAj7PPmlrB0WCkrHfE7iLqFo2XA-DKzmDw";
        Claims claims = Jwts.parser().setSigningKey("shop").parseClaimsJws(jwtToken).getBody();
        System.out.println(claims);
    }
}

