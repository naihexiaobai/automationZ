package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.wap.controller.service.MenuTreeService;
import com.wap.controller.service.PermissionService;
import com.wap.model.MenuTree;
import com.wap.model.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/25.
 */
@Controller
@RequestMapping("permissionCc")
public class PermissionCc extends PublicFunction {
    @Resource(name = "permissionService")
    private PermissionService permissionService;
    @Resource(name = "menuTreeService")
    private MenuTreeService menuTreeService;

    /**
     * 获得菜单
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getLeaveOther")
    @ResponseBody
    public JSONArray getLeaveOther(HttpServletRequest request, HttpServletResponse response) {
        String positioncode = request.getParameter("positioncode");
        List<Permission> permissions = permissionService.selectByPositionCode(Integer.valueOf(positioncode));
        List<MenuTree> menuTrees = menuTreeService.selectMenuByListCode("%");
        JSONArray jsonArray = new JSONArray();
        if (permissions.size() == 0) {
            getJSONArray(menuTrees, jsonArray);
            return jsonArray;
        } else {
            List<MenuTree> menuTrees1 = new ArrayList<MenuTree>();
            for (MenuTree menuTree : menuTrees) {
                for (Permission permission : permissions) {
                    if (permission.getMenuid() == menuTree.getId()) {
                        menuTree.setRemark("1");
                        break;
                    } else {
                        menuTree.setRemark("0");
                    }
                }
                menuTrees1.add(menuTree);
            }
            getJSONArray(menuTrees1, jsonArray);
        }
        return jsonArray;
    }

    @RequestMapping("insertIntoP")
    @ResponseBody
    @Transactional
    public boolean insertIntoP(HttpServletRequest request) {
        //标识
        int q = 0;
        String positionCode = request.getParameter("positioncode");
        //ids="&12@12.12&";    格式
        String ids = request.getParameter("ids");
        if (isStringNull(positionCode) || isStringNull(ids)) {
            return false;
        }
        //后插入
        List<Permission> list = getListSplitSpa(ids);
        if (list.size() > 0) {
            List<Permission> permissions = getListP(list, positionCode, "");
            q = permissionService.deleteByPositionCode(Integer.valueOf(positionCode));
            q = permissionService.insertList(permissions);
        } else {
            return false;
        }
        return q == list.size() ? true : false;
    }

    /**
     * 转换list<Permission>
     *
     * @param list
     * @param positionCode
     * @param remark
     * @return
     */
    @Transactional
    private List<Permission> getListP(List<Permission> list, String positionCode, String remark) {
        List<Permission> listP = new ArrayList<Permission>();
        //这里得到子目录
        for (int i = 0; i < list.size(); i++) {
            Permission permission = new Permission();
            permission.setMenuid(list.get(i).getMenuid());
            permission.setPositioncode(Integer.valueOf(positionCode).byteValue());
            permission.setRemark(remark);
            permission.setMenulistcode(list.get(i).getMenulistcode());
            listP.add(permission);
        }
//        //解析获得父目录，得到所有父节点 listcode  且   不重复
//        List<MenuTree> menuTrees = menuTreeService.getAll();
//        List<MenuTree> menuTrees1 = new ArrayList<MenuTree>();
//        for (MenuTree menuTree : menuTrees) {
//            if (menuTree.getListcode().split("\\.").length == 2) {
//                for (int i = 0; i < list.size(); i++) {
//                    String mlc = list.get(i).getMenulistcode();
//                    if (menuTree.getListcode().equals(mlc.split("\\.")[0] + "." + mlc.split("\\.")[1])) {
//                        menuTrees1.add(menuTree);
//                        break;
//                    }
//                }
//            }
//            if (menuTree.getListcode().split("\\.").length == 1) {
//                for (int i = 0; i < list.size(); i++) {
//                    String mlc = list.get(i).getMenulistcode();
//                    if (menuTree.getListcode().equals(mlc.split("\\.")[0])) {
//                        menuTrees1.add(menuTree);
//                        break;
//                    }
//                }
//            }
//        }
//        //这里得到父目录
//        for (MenuTree menuTree : menuTrees1) {
//            Permission permission = new Permission();
//            permission.setMenuid(menuTree.getId());
//            permission.setPositioncode(Integer.valueOf(positionCode).byteValue());
//            permission.setRemark(remark);
//            permission.setMenulistcode(menuTree.getListcode());
//            listP.add(permission);
//        }
        return listP;
    }
}
