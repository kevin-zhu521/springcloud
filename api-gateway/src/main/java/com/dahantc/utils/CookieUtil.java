package com.dahantc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by Kevin Zhu on 2019/1/26 19:08 .
 */
public class CookieUtil {

    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(Objects.nonNull(cookies)){
            for (Cookie cookie : cookies) {
                if(Objects.equals(cookie.getName(), name)){
                    return cookie;
                }
            }
        }
       return null;
    }
}
