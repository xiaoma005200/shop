package com.xiaoma.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Component
public class CookieUtils {

    /**
     * 得到Cookie的值(不解码)
     *
     * @param request    请求
     * @param cookieName Cookie名称
     * @return
     */
    public String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值(选择是否解码)
     *
     * @param request    请求
     * @param cookieName Cookie名称
     * @param isDecoder  是否解码
     * @return
     */
    public  String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值
     *
     * @param request      请求
     * @param cookieName   Cookie名称
     * @param encodeString 编码格式
     * @return
     */
    public  String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
     *
     * @param request     请求
     * @param response    响应
     * @param cookieName  Cookie名称
     * @param cookieValue Cookie值
     */
    public  void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值 在指定时间内生效,但不编码
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     */
    public  void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值 不设置生效时间
     *
     * @param request     请求
     * @param response    响应
     * @param cookieName  Cookie名称
     * @param cookieValue Cookie值
     * @param isEncode    是否编码
     */
    public  void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     * @param isEncode     是否编码
     */
    public  void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     * @param encodeString 编码格式
     */
    public  void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除Cookie带cookie域名
     *
     * @param request    请求
     * @param response   响应
     * @param cookieName Cookie名称
     */
    public  void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     * @param isEncode     是否编码
     */
    private  void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {
                // 设置域名的cookie
                cookie.setDomain("localhost");
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Cookie的值，并使其在指定时间内生效
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage cookie生效的最大秒数
     * @param encodeString 编码格式
     */
    private void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {
                // 设置域名的cookie,默认设置为localhost
                cookie.setDomain("localhost");
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}