package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wap.controller.service.MenuTreeService;
import com.wap.controller.service.UserService;
import com.wap.model.User;
import com.wap.util.DateUtil;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/7/25.
 */
@Controller
@RequestMapping("userCc")
public class UserCc extends PublicFunction {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "menuTreeService")
    private MenuTreeService menuTreeService;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @RequestMapping(value = "selectUserByPage")
    @ResponseBody
    public JSONObject selectUserByPage(HttpServletRequest request, HttpServletResponse response) {
        //页数
        String goPage = "1";
        //总条数
        int totalSize = 0;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        PagerUtil pagerUtil = new PagerUtil();
        totalSize = userService.selectCountId();
        goPage = request.getParameter("gopage");
        if (isStringNull(goPage) || totalSize < 1) {
            return null;
        }
        int pagenow = Integer.valueOf(goPage);
        pagerUtil.setRecordTotal(totalSize > 0 ? totalSize : 0);
        //校验最大最小页数
        pagenow = pagenow < 1 ? 1 : pagenow;
        pagenow = pagenow > pagerUtil.getPageTotal() ? pagerUtil.getPageTotal() : pagenow;
        pagerUtil.setDataSize(pagenow);
        List<User> users = userService.selectByPage(pagerUtil);
        getJSONArrayUser(users, jsonArray);
        jsonObject.put("jsonarray", jsonArray);
        jsonObject.put("pagenow", pagenow);
        return jsonObject;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public boolean addUser(HttpServletRequest request) {
        User user = new User();
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String jobnumber = request.getParameter("jobnumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String pos = position.split("&")[0];
        String poscode = position.split("&")[1];
        user.setName(name);
        user.setDepartment(department);
        user.setPosition(pos);
        user.setPositioncode(Integer.valueOf(poscode));
        user.setPhone(phone);
        //默认打开状态
        user.setStatus(Integer.valueOf(0).byteValue());
        user.setCreatetime(DateUtil.getDateFormat(new Date(), DateUtil.DATETIME_DEFAULT_FORMAT));
        user.setJobnumber(jobnumber);
        user.setUsername(username);
        user.setUserpassword(password);
        int q = userService.insertSelective(user);
        return q > 0 ? true : false;
    }

    @RequestMapping("deleteOff")
    @ResponseBody
    public boolean deleteOff(HttpServletRequest request) {
        int w = 0;
        String id = request.getParameter("id");
        User user = userService.selectByPrimaryKey(Integer.valueOf(id));
        if (user.getId() > 0) {
            int q = user.getStatus() == 0 ? 1 : 0;
            user.setStatus(Integer.valueOf(q).byteValue());
            w = userService.updateByPrimaryKeySelective(user);
        }
        return w > 0 ? true : false;
    }

    @RequestMapping("selectUserById")
    @ResponseBody
    public User selectUserById(HttpServletRequest request) {
        String idString = request.getParameter("id");
        if (isStringNull(idString)) {
            return null;
        }
        user = userService.selectByPrimaryKey(Integer.valueOf(idString));
        return user;
    }

    @RequestMapping("selectByUserName")
    @ResponseBody
    public boolean selectByUserName(HttpServletRequest request) {
        String username = request.getParameter("username");
        try {
            user = userService.getUserByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            return true;
        }
        return user.getId() > 0 ? false : true;
    }

    @RequestMapping("updateUp")
    @ResponseBody
    public boolean updateUp(HttpServletRequest request) {
        String id = request.getParameter("id");
        String position = request.getParameter("position");
        String pos = position.split("&")[0];
        String poscode = position.split("&")[1];
        User user = userService.selectByPrimaryKey(Integer.valueOf(id));
        user.setPositioncode(Integer.valueOf(poscode));
        user.setPosition(pos);
        int q = userService.updateByPrimaryKeySelective(user);
        return q > 0 ? true : false;
    }

    @RequestMapping(value = "updateUser")
    @ResponseBody
    public boolean updateUser(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        user.setId(Integer.valueOf(id));
        user.setName(name);
        user.setDepartment(department);
        user.setPosition(position);
        user.setPhone(phone);
        int q = userService.updateByPrimaryKeySelective(user);
        return q > 0 ? true : false;
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public boolean updatePassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String id = session.getAttribute("uid").toString();
        User user = userService.selectByPrimaryKey(Integer.valueOf(id));
        if (user.getUserpassword().equals(password)) {
            user.setUserpassword(password1);
        } else {
            return false;
        }
        int q = userService.updateByPrimaryKeySelective(user);
        return q > 0 ? true : false;
    }
}
