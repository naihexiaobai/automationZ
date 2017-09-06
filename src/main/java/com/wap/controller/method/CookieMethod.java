package com.wap.controller.method;

import com.wap.model.User;
import com.wap.util.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by admin on 2017/7/24.
 */
public class CookieMethod {

    //超时时间(单位：s)
    private static final int outTime = 10 * 60;

    /**
     * 初始化cookie
     *
     * @return
     */
    private static boolean initCookie(HttpServletRequest request, User user, String name, String passWord, HttpServletResponse response) {
        boolean status = false;
        try {
            if (user == null) {
                status = false;
            } else if (user.getUserpassword().equals(passWord)) {
                status = true;
                String enPassword = MD5Util.getEncryptedPwd(passWord);
                Cookie cookie = new Cookie("password", enPassword);
                cookie.setMaxAge(outTime);
                response.addCookie(cookie);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 是否需要验证
     *
     * @param request
     * @return
     */
    public static boolean passOrNo(HttpServletRequest request) {
        return ifHaveCookie(request.getCookies());
    }

    /**
     * 登录验证
     *
     * @return
     */
    public static boolean loginCookie(HttpServletRequest request, User user, String name, String passWord, HttpServletResponse response) {
        boolean status = false;
        try {
            Cookie[] cookies = request.getCookies();
            boolean result = ifHaveCookie(cookies);
            if (result) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("password")) {
                        if (user != null) {
                            status = MD5Util.validPassword(user.getUserpassword(), passWord);
                            break;
                        }
                    }
                }
            } else {
                status = initCookie(request, user, name, passWord, response);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 是否含指定cookie
     *
     * @param cookies
     * @return
     */
    private static boolean ifHaveCookie(Cookie[] cookies) {
        boolean status = false;
        if (cookies == null) {
            status = false;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("password")&&cookie.getValue()!=null) {
                    status = true;
                    break;
                }
            }
        }
        return status;
    }

    /**
     * 清除cookie
     * @param response
     */
    public static void closeCook(HttpServletResponse response) {
        Cookie cookie = new Cookie("password", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
