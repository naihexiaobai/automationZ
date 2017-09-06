package com.wap.controller;

import com.wap.controller.method.CookieMethod;
import com.wap.controller.method.SessionMethod;
import com.wap.controller.service.UserService;
import com.wap.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2017/7/18.
 */
@Controller
@RequestMapping("login")
public class Login {
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 登录
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "loginIn")
    public String loginIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String userName = request.getParameter("name");
            String userPassword = request.getParameter("password");
            User user = userService.getUserByUserName(userName);
            if (CookieMethod.passOrNo(request)) {
                return "autogo/index";
            }
            if (user == null) {
                return "login";
            } else {
                boolean status = CookieMethod.loginCookie(request, user, userName, userPassword, response);
                if (status) {
                    SessionMethod.setSessionValue(user, "name", request.getSession());
                    return "autogo/index";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }

    @RequestMapping(value = "loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        SessionMethod.closedSession(request);
        CookieMethod.closeCook(response);
        return "login";
    }
}
