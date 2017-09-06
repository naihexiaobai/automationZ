package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wap.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/25.
 */
public class PublicFunction {
    //递归，形成菜单树顺序
    public List<MenuTree> getS(List<MenuTree> menuTrees, String listFatherCode, List<MenuTree> mm) {
        for (MenuTree menuTree : menuTrees) {
            if (menuTree.getListfathercode().equals(listFatherCode)) {
                mm.add(menuTree);
                getS(menuTrees, menuTree.getListcode(), mm);
            }
        }
        return mm;
    }

    /**
     * 车体图片
     *
     * @param imageAboutAp
     * @param jsonObject
     * @return
     */
    public JSONObject getJSONObjectImage(ImageAboutAp imageAboutAp, JSONObject jsonObject) {
        jsonObject.put("apid", imageAboutAp.getApId());
        jsonObject.put("id", imageAboutAp.getId());
        jsonObject.put("up", isStringNull(imageAboutAp.getImgUp()) ? "" : imageAboutAp.getImgUp());
        jsonObject.put("down", isStringNull(imageAboutAp.getImgDown()) ? "" : imageAboutAp.getImgDown());
        jsonObject.put("left", isStringNull(imageAboutAp.getImgLeft()) ? "" : imageAboutAp.getImgLeft());
        jsonObject.put("right", isStringNull(imageAboutAp.getImgRight()) ? "" : imageAboutAp.getImgRight());
        jsonObject.put("front", isStringNull(imageAboutAp.getImgFront()) ? "" : imageAboutAp.getImgFront());
        jsonObject.put("behind", isStringNull(imageAboutAp.getImgBehind()) ? "" : imageAboutAp.getImgBehind());
        jsonObject.put("electrics", isStringNull(imageAboutAp.getImgElectrics()) ? "" : imageAboutAp.getImgElectrics());
        jsonObject.put("machine", isStringNull(imageAboutAp.getImgMachine()) ? "" : imageAboutAp.getImgMachine());
        return jsonObject;
    }

    /**
     * 产品订单之配件
     *
     * @param fitingLists
     * @param jsonArray
     * @return
     */
    public JSONArray getJSONArrayFiting(List<FitingList> fitingLists, JSONArray jsonArray) {
        for (FitingList fitingList : fitingLists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", fitingList.getId());
            jsonObject.put("bdid", fitingList.getBasedatsid());
            jsonObject.put("apid", fitingList.getAutoproductid());
            jsonObject.put("bdname", fitingList.getBdname());
            jsonObject.put("bdsp", fitingList.getBdspecifications());
            jsonObject.put("createuser", fitingList.getCreateuser());
            jsonObject.put("createtime", fitingList.getCreatetime());
            jsonObject.put("number", fitingList.getNumbers());
            jsonObject.put("status", fitingList.getStatus());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 订单
     *
     * @param autoProducts
     * @param jsonArray
     * @return
     */
    public JSONArray getJSONArrayAp(List<AutoProduct> autoProducts, JSONArray jsonArray) {
        for (AutoProduct autoProduct : autoProducts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", autoProduct.getId());
            jsonObject.put("agent", autoProduct.getAgent());
            jsonObject.put("createtime", autoProduct.getCreatetime());
            jsonObject.put("createuser", autoProduct.getCreateuser());
            jsonObject.put("name", autoProduct.getName());
            jsonObject.put("remark", autoProduct.getRemark());
            jsonObject.put("specifications", autoProduct.getSpecifications());
            jsonObject.put("status", autoProduct.getStatus());
            jsonObject.put("updatetime", autoProduct.getUpdatetime());
            jsonObject.put("uid", autoProduct.getCreateuserid());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 菜单
     *
     * @param oneLevel
     * @param jsonArray
     * @return
     */
    public JSONArray getJSONArray(List<MenuTree> oneLevel, JSONArray jsonArray) {
        for (MenuTree s : oneLevel) {
            JSONObject jsonObject = new JSONObject();
            String remark = isStringNull(s.getRemark()) ? "0" : s.getRemark();
            String url = isStringNull(s.getUrl()) ? "#" : s.getUrl();
            jsonObject.put("id", s.getId());
            jsonObject.put("url", url);
            jsonObject.put("name", s.getName());
            jsonObject.put("status", s.getStatus());
            jsonObject.put("remark", remark);
            jsonObject.put("listCode", s.getListcode());
            jsonObject.put("listFatherCode", s.getListfathercode());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 基础信息
     *
     * @param baseDats
     * @param jsonArray
     * @return
     */
    public JSONArray getJSONArrayBD(List<BaseDats> baseDats, JSONArray jsonArray) {
        for (BaseDats s : baseDats) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", s.getId());
            jsonObject.put("levelcode", s.getLevelcode());
            jsonObject.put("fatherlevelcode", isStringNull(s.getFatherlevelcode()) ? "" : s.getFatherlevelcode());
            jsonObject.put("specifications", isStringNull(s.getSpecifications()) ? "" : s.getSpecifications());
            jsonObject.put("shortname", s.getShortname());
            jsonObject.put("fullname", s.getFullname());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * 权限
     *
     * @param users
     * @param jsonArray
     */
    public void getJSONArrayUser(List<User> users, JSONArray jsonArray) {
        for (User user : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());
            jsonObject.put("department", user.getDepartment());
            jsonObject.put("status", user.getStatus());
            jsonObject.put("position", user.getPosition());
            jsonObject.put("phone", user.getPhone());
            jsonArray.add(jsonObject);
        }
    }

    /**
     * 判断是否为空
     *
     * @param string
     * @return
     */
    public boolean isStringNull(String string) {
        return (string == null ? true : false) || (string.equals("") ? true : false);
    }

    /**
     * 转换数字字符串为byte
     *
     * @param stringInt
     * @return
     */
    public byte stringToByte(String stringInt) {
        return isStringNull(stringInt) ? -1 : Integer.valueOf(stringInt).byteValue();
    }

    /**
     * 通过截取 &  字符串获得集合
     *
     * @param string
     * @return
     */
    public List<String> getListSplit(String string) {
        List<String> list = new ArrayList<String>();
        int slength = string.split("&").length;
        for (int i = 0; i < slength; i++) {
            if (isStringNull(string.split("&")[i])) {
            } else {
                list.add(string.split("&")[i]);
            }
        }
        return list;
    }

    /**
     * 特殊格式"&12@12.12&"
     *
     * @param string
     * @return
     */
    public List<Permission> getListSplitSpa(String string) {
        List<Permission> list = new ArrayList<Permission>();
        int slength = string.split("&").length;
        for (int i = 0; i < slength; i++) {
            if (isStringNull(string.split("&")[i])) {
            } else {
                Permission permission = new Permission();
                permission.setMenuid(Integer.valueOf(((string.split("&")[i]).split("@"))[0]));
                permission.setMenulistcode(((string.split("&")[i]).split("@"))[1]);
                list.add(permission);
            }
        }
        return list;
    }
}
