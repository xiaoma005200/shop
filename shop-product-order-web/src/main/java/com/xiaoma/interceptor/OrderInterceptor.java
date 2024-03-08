package com.xiaoma.interceptor;

import com.xiaoma.util.CookieUtils;
import com.xiaoma.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OrderInterceptor implements HandlerInterceptor {

    @Autowired
    CookieUtils cookieUtils;

    /* 目标方法执行前执行*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取用户相关的参数
        String userId = request.getHeader("userId");
        String username = request.getHeader("username");
        String userKey = cookieUtils.getCookieValue(request, "user-key", true);

        // 2.封装用户信息
        UserInfo userInfo = new UserInfo(userId, username, userKey);

        // 3.放入request域中
        request.setAttribute("userInfo", userInfo);
        // 4.放行
        return true;
    }

}
