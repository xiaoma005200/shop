package com.xiaoma.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JWTTokenUtils {

    /**
    * 生成JWT令牌
    * @param id  唯一标识
    * @param subject 主题
    * @param secretKey 密钥(加密解密使用)
    * @param params (自定义参数)
    * @return
    */
    public String generateJwtToken(String id, String subject, String secretKey, Map<String,Object> params){
        //根据map设置clamis
        return Jwts.builder().setId(id).setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setClaims(params).compact();
    }

    /**
     * 获取JWT令牌
     * @param secretKey 密钥(加密解密使用)
     * @param token
     * @return
     */
    public String parseToken(String secretKey,String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().toString();
    }

}