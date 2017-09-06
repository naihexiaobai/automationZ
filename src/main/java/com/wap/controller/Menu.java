package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wap.controller.service.MenuTreeService;
import com.wap.controller.service.PermissionService;
import com.wap.model.MenuTree;
import com.wap.model.Permission;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/20.
 */
@Controller
@RequestMapping("menu")
public class Menu extends PublicFunction {
    @Resource(name = "menuTreeService")
    private MenuTreeService menuTreeService;
    @Resource(name = "permissionService")
    private PermissionService permissionService;

    @RequestMapping("/getMenuByListCode")
    @ResponseBody//必须加入的注解
    public JSONArray getMenuByListCode(HttpServletRequest request) {
        String ss = "%.%.%";
        List<MenuTree> menus = menuTreeService.selectMenuByListCode(ss);
        JSONArray jsonArray = new JSONArray();
        List<MenuTree> oneLevel = new ArrayList<MenuTree>();
        getS(menus, "0", oneLevel);
        getJSONArray(oneLevel, jsonArray);
        return jsonArray;
    }

    @RequestMapping("/getAll")
    @ResponseBody//必须加入的注解
    public JSONArray getAll(HttpServletRequest request) {
        //过滤
        JSONArray jsonArray = new JSONArray();
        List<MenuTree> menuTrees = menuTreeService.getAll();
        HttpSession session = request.getSession(false);
        String positioncode = session.getAttribute("positioncode").toString();
        List<Permission> permissions = permissionService.selectByPositionCode(Integer.valueOf(positioncode));
        if (permissions.size() > 0) {
            List<MenuTree> menuTrees1 = new ArrayList<MenuTree>();
            for (MenuTree menuTree : menuTrees) {
                for (Permission permission : permissions) {
                    if (menuTree.getId() == permission.getMenuid()) {
                        menuTrees1.add(menuTree);
                        break;
                    }
                }
            }
            List<MenuTree> oneLevel = new ArrayList<MenuTree>();
            getS(menuTrees1, "0", oneLevel);
            getJSONArray(oneLevel, jsonArray);
        }
        return jsonArray;
    }

    @RequestMapping("/getAllStatusByPage")
    @ResponseBody//必须加入的注解
    public JSONObject getAllStatusByPage(HttpServletRequest request) {
        PagerUtil pagerUtil = new PagerUtil();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        //页数
        String goPage = "1";
        //总条数
        int totalSize = 0;
        goPage = request.getParameter("gopage");
        totalSize = menuTreeService.selectAllStatusCount();
        if (isStringNull(goPage) || totalSize < 1) {
            return null;
        }
        int pagenow = Integer.valueOf(goPage);
        pagerUtil.setRecordTotal(totalSize > 0 ? totalSize : 0);
        //校验最大最小页数
        pagenow = pagenow < 1 ? 1 : pagenow;
        pagenow = pagenow > pagerUtil.getPageTotal() ? pagerUtil.getPageTotal() : pagenow;
        pagerUtil.setDataSize(pagenow);
        List<MenuTree> menuTrees = menuTreeService.selectAllStatusByPage(pagerUtil);
        getJSONArray(menuTrees, jsonArray);
        jsonObject.put("jsonarray", jsonArray);
        jsonObject.put("pagenow", pagenow);
        return jsonObject;
    }

    /**
     * 更改菜单使用状态
     *
     * @param request
     * @return
     */
    @RequestMapping("changeMenuStatus")
    @ResponseBody
    public boolean changeMenuStatus(HttpServletRequest request) {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        if (isStringNull(id) || isStringNull(type)) {
            return false;
        }
        MenuTree menuTree = menuTreeService.selectByPrimaryKey(Integer.valueOf(id));
        //启用
        if (Integer.valueOf(type) == 1) {
            menuTree.setStatus(Integer.valueOf("1").byteValue());
        }
        //禁用
        else if (Integer.valueOf(type) == 0) {
            menuTree.setStatus(Integer.valueOf("0").byteValue());
        }
        try {
            menuTreeService.selectByIdUpdateStatus(menuTree);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 修改菜单信息
     *
     * @param request
     * @return
     */
    @RequestMapping("updateMenuTreeSome")
    @ResponseBody
    public boolean updateMenuTreeSome(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String listcode = request.getParameter("listcode");
        String listfathercode = request.getParameter("listfathercode");
        if (isStringNull(id) || isStringNull(name) || isStringNull(url) || isStringNull(listcode) || isStringNull(listfathercode)) {
            return false;
        }
        MenuTree menuTree = menuTreeService.selectByPrimaryKey(Integer.valueOf(id));
        menuTree.setListcode(listcode);
        menuTree.setName(name);
        menuTree.setListfathercode(listfathercode);
        menuTree.setUrl(url);
        int q = menuTreeService.updateByPrimaryKeySelective(menuTree);
        return q > 0 ? true : false;
    }

    /**
     * 查询父目录
     *
     * @param request
     * @return
     */
    @RequestMapping("selectByLC")
    @ResponseBody
    public JSONArray selectByLC(HttpServletRequest request) {
        String listCode = "%.%.%";
        List<MenuTree> menuTrees = menuTreeService.selectGruopByLC(listCode);
        JSONArray jsonArray = new JSONArray();
        getJSONArray(menuTrees, jsonArray);
        return jsonArray;
    }

    @RequestMapping("addMenuTreeSome")
    @ResponseBody
    public boolean addMenuTreeSome(HttpServletRequest request) {
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String listfathercode = request.getParameter("listfathercode");
        String listcode = "";
        if (isStringNull(name) || isStringNull(url) || isStringNull(listfathercode)) {
            return false;
        }
        List<MenuTree> menuTrees = menuTreeService.selectByLFC(listfathercode);
        int id = 0;
        for (MenuTree menuTree : menuTrees) {
            if (id < menuTree.getId()) {
                id = menuTree.getId();
            }
        }
        for (MenuTree menuTree : menuTrees) {
            if (id == menuTree.getId()) {
                String lc = menuTree.getListcode();
                String l = lc.split("\\.")[lc.split("\\.").length - 1];
                for (int i = 0; i < lc.split("\\.").length - 1; i++) {
                    listcode += lc.split("\\.")[i] + ".";
                }
                listcode += (Integer.valueOf(l) + 1);
            }
        }
        if (isStringNull(listcode)) {
            listcode += listfathercode + ".1";
        }
        MenuTree menuTree = new MenuTree();
        menuTree.setUrl(url);
        menuTree.setListfathercode(listfathercode);
        menuTree.setName(name);
        menuTree.setListcode(listcode);
        menuTree.setStatus(Integer.valueOf("1").byteValue());
        int q = menuTreeService.insertSelective(menuTree);
        return q > 0 ? true : false;
    }

}
