package com.xiaoma.interceptor;

import com.xiaoma.util.CookieUtils;
import com.xiaoma.vo.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class CartInterceptor  implements HandlerInterceptor {

    @Autowired
    CookieUtils cookieUtils;

    /*
    *
    * 目标方法执行前执行
    *
    **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");

        UserInfo userInfo = new UserInfo();
        if (StringUtils.isNotBlank(userId)) {
            //说明用户已经登录
            userInfo.setUserId(userId);
            userInfo.setUsername(request.getHeader("username"));
        }

        //获取cookie中的临时用户
        String cookieValue = cookieUtils.getCookieValue(request, "user-key", true);
        if (StringUtils.isBlank(cookieValue)) {
            userInfo.setUserKey(UUID.randomUUID().toString());
        } else {
            userInfo.setUserKey(cookieValue);
        }

        //将UserInfo放入到request域中
        request.setAttribute("userInfo",userInfo);

        return true;//放行
    }

    /*
    * 目标方法执行后执行
    * 视图响应之前
    */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserInfo userInfo = (UserInfo)request.getAttribute("userInfo");

        //给浏览器写一个临时用户cookie
        cookieUtils.setCookie(request,response,"user-key",userInfo.getUserKey(),30*24*60*60,true);
    }
}
