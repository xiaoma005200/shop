package com.xiaoma.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    /*登录用户的唯一标识,将来在redis中存储登录用户的购物车将它作为key(cart:userId)*/
    private String userId;

    /*登录名*/
    private String username;

    /*临时用的key,将来在redis中存储临时用户的购物车key(cart:userKey)*/
    private String userKey;
}
