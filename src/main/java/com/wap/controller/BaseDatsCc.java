package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.wap.controller.service.BaseDatsService;
import com.wap.model.BaseDats;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/28.
 * 基础信息类
 */

@Controller
@RequestMapping("baseDatsCc")
public class BaseDatsCc extends PublicFunction {
    @Resource(name = "baseDatsService")
    private BaseDatsService baseDatsService;

    /**
     * 根据父code  查找子目录
     *
     * @param request
     * @return
     */
    @RequestMapping("getDBByLevelCode")
    @ResponseBody
    public JSONArray getDBByLevelCode(HttpServletRequest request) {
        String fatherLevelCode = request.getParameter("fathercode");
        if (isStringNull(fatherLevelCode)) {
            fatherLevelCode = "";
        }
        List<BaseDats> baseDats = baseDatsService.selectByFatherLevelCode(fatherLevelCode);
        if (baseDats.size() < 1) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray = getJSONArrayBD(baseDats, jsonArray);
        return jsonArray;
    }

    /**
     * 模糊查询
     *
     * @param request
     * @return
     */
    @RequestMapping("getDBBySome")
    @ResponseBody
    public JSONArray getDBBySome(HttpServletRequest request) {
        List<BaseDats> baseDats = new ArrayList<BaseDats>();
        JSONArray jsonArray = new JSONArray();
        String string = request.getParameter("str");
        //type:1,2,3  ---简称，全称，型号
        String type = request.getParameter("type");
        if (isStringNull(string) || isStringNull(type)) {
            return null;
        }
        int ty = Integer.valueOf(type);
        if (ty == 1) {
            baseDats = baseDatsService.selectByS("%" + string + "%");
        } else if (ty == 2) {
            baseDats = baseDatsService.selectByF("%" + string + "%");
        } else if (ty == 3) {
            baseDats = baseDatsService.selectBySp("%" + string + "%");
        }
        jsonArray = getJSONArrayBD(baseDats, jsonArray);
        return jsonArray;
    }

    /**
     * 获取目录列表
     *
     * @param request
     * @return
     */
    @RequestMapping("getDBLevelCode")
    @ResponseBody
    public JSONArray getDBLevelCode(HttpServletRequest request) {
        List<BaseDats> baseDats = baseDatsService.selectByNotLikeLevelCode();
        JSONArray jsonArray = new JSONArray();
        jsonArray = getJSONArrayBD(baseDats, jsonArray);
        return jsonArray;
    }


    @RequestMapping("getDBLikeLevelCode")
    @ResponseBody
    public JSONArray getDBLikeLevelCode(HttpServletRequest request) {
        String levelcode = request.getParameter("levelcode");
        List<BaseDats> baseDats = baseDatsService.selectByLikeLevelCode(levelcode);
        JSONArray jsonArray = new JSONArray();
        jsonArray = getJSONArrayBD(baseDats, jsonArray);
        return jsonArray;
    }

    /**
     * 增
     *
     * @param request
     * @return
     */
    @RequestMapping("insertIntoBD")
    @ResponseBody
    public boolean insertIntoBD(HttpServletRequest request) {
        String shortName = request.getParameter("shortname");
        String fullName = request.getParameter("fullname");
        String levelCode = request.getParameter("levelcode");
        String fatherlevelcode = request.getParameter("fatherlevelcode");
        String attribute = request.getParameter("attribute");
        String specifications = request.getParameter("specifications");
        BaseDats baseDats = new BaseDats();
        baseDats.setFullname(fullName);
        baseDats.setShortname(shortName);
        baseDats.setSpecifications(specifications);
        baseDats.setAttribute(Integer.valueOf(attribute).byteValue());
        baseDats.setFatherlevelcode(fatherlevelcode);
        baseDats.setLevelcode(levelCode);
        int q = baseDatsService.insertSelective(baseDats);
        return q > 0 ? true : false;
    }

    /**
     * 自动化产品查询多条件
     *
     * @param request
     * @return
     */
    @RequestMapping("getDBBySSFM")
    @ResponseBody
    public JSONArray getDBBySSFM(HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        List<BaseDats> dateBases = new ArrayList<BaseDats>();
        String fullname = request.getParameter("fullname");
        String db_spq = request.getParameter("db_spq");
        String db_shortname = request.getParameter("db_shortname");
        String flevelcode = request.getParameter("flevelcode");
        String type = request.getParameter("type");
        int num = Integer.valueOf(type);
        int id = 0;
        if (num == 1) {
            id = isStringNull(db_spq) ? 0 : Integer.valueOf(db_spq);
            id = isStringNull(fullname) ? 0 : Integer.valueOf(fullname);
            id = isStringNull(db_shortname) ? 0 : Integer.valueOf(db_shortname);
            BaseDats baseDats = new BaseDats();
            baseDats = baseDatsService.selectByPrimaryKey(id);
            if (baseDats.getId() > 0) {
                dateBases.add(baseDats);
            }
        } else if (num == 2) {
            dateBases = baseDatsService.selectByFatherLevelCode(flevelcode);
        }
        if (dateBases.size() > 0) {
            jsonArray = getJSONArrayBD(dateBases, jsonArray);
            return jsonArray;
        }
        return null;
    }


}
