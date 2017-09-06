package com.wap.controller.method;

import com.wap.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2017/7/24.
 */
public class SessionMethod {

    private static final int outTime = 60 * 10;

    private HttpSession createSession(HttpServletRequest request) {
        return request.getSession(true);
    }

    public static void setSessionValue(User user, String valuesName, HttpSession session) {
        session.setAttribute("name", user.getName());
        session.setAttribute("positioncode", user.getPositioncode());
        session.setAttribute("uid",user.getId());
        session.setMaxInactiveInterval(outTime);
    }

    public static void closedSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
