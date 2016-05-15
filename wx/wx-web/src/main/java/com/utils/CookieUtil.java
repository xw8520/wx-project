package com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Admin on 2016/5/15.
 */
public class CookieUtil {
    /**
     * 根据cookie的名称获取cookie
     *
     * @param req
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest req, String name) {
        Cookie cookies[] = req.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                //&& req.getServerName().equals(cookies[i].getDomain())) {
                return cookies[i];
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest req, String name) {
        Cookie ck = getCookie(req, name);
        if (ck != null) {
            return ck.getValue();
        } else {
            return null;
        }
    }

    /**
     * 删除cookie
     *
     * @param req
     * @param resp
     * @param cookie
     */
    public static void deleteCookie(HttpServletRequest req,
                                    HttpServletResponse resp, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath(getPath(req));
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
    }

    /**
     * 设置cookie
     *
     * @param req
     * @param resp
     * @param name
     * @param value    如果不设置时间，默认永久
     */
    public static void setCookie(HttpServletRequest req,
                                 HttpServletResponse resp, String name, String value) {
        setCookie(req, resp, name, value, 0x278d00);
    }


    /**
     * @param req
     * @param resp
     * @param name
     * @param value
     * @param maxAge   设置cookie，设定时间
     */
    public static void setCookie(HttpServletRequest req,
                                 HttpServletResponse resp, String name, String value, int maxAge) {
        Cookie cookie = null;
        try {
            cookie = new Cookie(name, value == null ? "" : URLEncoder.encode(value.replaceAll("\r\n", ""), "utf-8"));
        } catch (UnsupportedEncodingException e) {
        }
        cookie.setMaxAge(maxAge);
        cookie.setPath(getPath(req));
        resp.addCookie(cookie);
    }

    private static String getPath(HttpServletRequest req) {
        String path = req.getContextPath();
        return (path == null || path.length() == 0) ? "/" : path;
    }
}
