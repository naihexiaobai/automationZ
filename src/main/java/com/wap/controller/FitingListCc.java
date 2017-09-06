package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.wap.controller.service.BaseDatsService;
import com.wap.controller.service.FitingListService;
import com.wap.model.BaseDats;
import com.wap.model.FitingList;
import com.wap.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/8/2.
 * 订单配件
 */
@Controller
@RequestMapping("fitingListCc")
public class FitingListCc extends PublicFunction {
    @Resource(name = "fitingListService")
    private FitingListService fitingListService;
    @Resource(name = "baseDatsService")
    private BaseDatsService baseDatsService;

    /**
     * 查询订单详情
     *
     * @param request
     * @return
     */
    @RequestMapping("selectByAPId")
    @ResponseBody
    public JSONArray selectByAPId(HttpServletRequest request) {
        String apid = request.getParameter("apid");
        JSONArray jsonArray = new JSONArray();
        List<FitingList> fitingLists = new ArrayList<FitingList>();
        if (isStringNull(apid)) {
            return null;
        }
        fitingLists = fitingListService.selectByAPId(Integer.valueOf(apid));
        jsonArray = getJSONArrayFiting(fitingLists, jsonArray);
        return jsonArray;
    }

    /**
     * 分类查询
     *
     * @param request
     * @return
     */
    @RequestMapping("selectByAPIdClasses")
    @ResponseBody
    public JSONArray selectByAPIdClasses(HttpServletRequest request) {
        String apid = request.getParameter("apid");
        String fatherLevelCode = request.getParameter("fatherLevelCode");
        JSONArray jsonArray = new JSONArray();
        List<FitingList> fitingLists = new ArrayList<FitingList>();
        List<FitingList> fitingLists1 = new ArrayList<FitingList>();
        if (isStringNull(apid) || isStringNull(fatherLevelCode)) {
            return null;
        }
        fitingLists = fitingListService.selectByAPId(Integer.valueOf(apid));
        try {
            for (FitingList fitingList : fitingLists) {
                if (!isStringNull(fitingList.getRemark()) && fitingList.getRemark().contains(fatherLevelCode)) {
                    fitingLists1.add(fitingList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray = getJSONArrayFiting(fitingLists1, jsonArray);
        return jsonArray;
    }

    /**
     * 向订单新增配件
     *
     * @param request
     * @return
     */
    @RequestMapping("addBdToFl")
    @ResponseBody
    public boolean addBdToFl(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String apid = request.getParameter("apid");
        String levelcode = request.getParameter("levelcode");
        String number = request.getParameter("number");
        if (isStringNull(apid) || isStringNull(levelcode) || isStringNull(number)) {
            return false;
        }
        FitingList fitingList = new FitingList();
        BaseDats baseDats = baseDatsService.selectByLevelCode(levelcode).get(0);
        fitingList.setAutoproductid(Integer.valueOf(apid));
        fitingList.setBasedatsid(baseDats.getId());
        fitingList.setBdname(baseDats.getShortname());
        fitingList.setBdspecifications(baseDats.getSpecifications());
        fitingList.setNumbers(Integer.valueOf(number));
        fitingList.setCreateuser(session.getAttribute("name").toString());
        fitingList.setCreatetime(DateUtil.getDateFormat(new Date(), DateUtil.DATETIME_DEFAULT_FORMAT));
        fitingList.setStatus(Integer.valueOf(1).byteValue());
        fitingList.setRemark(baseDats.getLevelcode());
        int q = fitingListService.insertSelective(fitingList);
        return q > 0 ? true : false;
    }

    /**
     * 删除
     *
     * @param request
     * @return
     */
    @RequestMapping("deleteFlById")
    @ResponseBody
    public boolean deleteFlById(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (isStringNull(id)) {
            return false;
        }
        int q = fitingListService.deleteByPrimaryKey(Integer.valueOf(id));
        return q > 0 ? true : false;
    }

    @RequestMapping("updateFlById")
    @ResponseBody
    public boolean updateFlById(HttpServletRequest request) {
        FitingList fitingList = new FitingList();
        String id = request.getParameter("id");
        String number = request.getParameter("number");
        if (isStringNull(id) || isStringNull(number)) {
            return false;
        }
        fitingList = fitingListService.selectByPrimaryKey(Integer.valueOf(id));
        fitingList.setNumbers(Integer.valueOf(number));
        int q = fitingListService.updateByPrimaryKeySelective(fitingList);
        return q > 0 ? true : false;
    }
}
