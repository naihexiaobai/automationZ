package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.wap.controller.service.AutoProductService;
import com.wap.model.AutoProduct;
import com.wap.model.AutoProductSelectPage;
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
 * Created by admin on 2017/8/1.
 */
@Controller
@RequestMapping("autoProductCc")
public class AutoProductCc extends PublicFunction {
    @Resource(name = "autoProductService")
    private AutoProductService autoProductService;

    /**
     * 多条件查询
     *
     * @param request
     * @return
     */
    @RequestMapping("selectByAutoproduct")
    @ResponseBody
    public JSONArray selectByAutoproduct(HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        List<AutoProduct> autoProducts = new ArrayList<AutoProduct>();
//        AutoProduct autoProduct = new AutoProduct();
        AutoProductSelectPage autoProduct = new AutoProductSelectPage();
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String agnet = request.getParameter("agnet");
        String createuser = request.getParameter("createuser");
        String createtime = request.getParameter("createtime");
        String gopage = request.getParameter("gopage");
        //此字段放置---订单编号
        String remark = request.getParameter("remark");
        int pagenow = 1;
        if (!isStringNull(name)) {
            autoProduct.setName(name);
        }
        if (!isStringNull(status)) {
            autoProduct.setStatus(Integer.valueOf(status).byteValue());
        }
        if (!isStringNull(agnet)) {
            autoProduct.setAgent(agnet);
        }
        if (!isStringNull(createuser)) {
            autoProduct.setCreateuser(createuser);
        }
        if (!isStringNull(createtime)) {
            autoProduct.setCreatetime("%" + createtime + "%");
        }
        if (!isStringNull(remark)) {
            autoProduct.setRemark(remark);
        }
        if (!isStringNull(gopage)) {
            pagenow = Integer.valueOf(gopage);
            //校验最大最小页数
            pagenow = pagenow < 1 ? pagenow = 1 : pagenow;
            autoProduct.setData(pagenow);
            int size = autoProductService.selectByApCount(autoProduct);
            autoProduct.setTotalSize(size);
            pagenow = pagenow > autoProduct.getMaxPage() ? autoProduct.getMaxPage() : pagenow;
        }
        autoProducts = autoProductService.selectByAp(autoProduct);
        if (autoProducts.size() < 1 && pagenow == 1) {
            return null;
        }
        jsonArray = getJSONArrayAp(autoProducts, jsonArray);
        return jsonArray;
    }

    /**
     * 新增
     *
     * @param request
     * @return
     */
    @RequestMapping("apAdd")
    @ResponseBody
    public boolean apAdd(HttpServletRequest request) {
        String name = request.getParameter("name");
        String agent = request.getParameter("agent");
        String specifications = request.getParameter("specifications");
        String compentid = request.getParameter("compentid");
        HttpSession session = request.getSession(false);
        AutoProduct autoProduct = new AutoProduct();
        autoProduct.setAgent(agent);
        autoProduct.setName(name);
        //此字段存放订单号----公司系统内部单号
        autoProduct.setRemark(compentid);
        autoProduct.setSpecifications(specifications);
        autoProduct.setCreatetime(DateUtil.getDateFormat(new Date(), DateUtil.DATETIME_DEFAULT_FORMAT));
        autoProduct.setUpdatetime(DateUtil.getDateFormat(new Date(), DateUtil.DATETIME_DEFAULT_FORMAT));
        autoProduct.setStatus(Integer.valueOf(0).byteValue());
        autoProduct.setCreateuser(session.getAttribute("name").toString());
        autoProduct.setCreateuserid(Integer.valueOf(session.getAttribute("uid").toString()));
        int q = autoProductService.insertSelective(autoProduct);
        return q > 0 ? true : false;
    }

    /**
     * 修改状态------审核
     *
     * @param request
     * @return
     */
    @RequestMapping("updateApStatus")
    @ResponseBody
    public boolean updateApStatus(HttpServletRequest request) {
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        if (isStringNull(id) || isStringNull(type)) {
            return false;
        }
        int typeI = Integer.valueOf(type);
        AutoProduct autoProduct = autoProductService.selectByPrimaryKey(Integer.valueOf(id));
        if (typeI == 1) {
            autoProduct.setStatus(Integer.valueOf("3").byteValue());
        } else {
            autoProduct.setStatus(Integer.valueOf("4").byteValue());
        }
        int q = autoProductService.updateByPrimaryKeySelective(autoProduct);
        return q > 0 ? true : false;
    }

    /**
     * 修改状态全部
     *
     * @param request
     * @return
     */
    @RequestMapping("changeApSttatus")
    @ResponseBody
    public boolean changeApSttatus(HttpServletRequest request) {
        AutoProduct autoProduct = new AutoProduct();
        String apid = request.getParameter("id");
        String status = request.getParameter("status");
        if (isStringNull(apid) || isStringNull(status)) {
            return false;
        }
        autoProduct = autoProductService.selectByPrimaryKey(Integer.valueOf(apid));
        autoProduct.setStatus(Integer.valueOf(status).byteValue());
        autoProduct.setUpdatetime(DateUtil.getDateFormat(new Date(), DateUtil.DATETIME_DEFAULT_FORMAT));
        int q = autoProductService.updateByPrimaryKeySelective(autoProduct);
        return q > 0 ? true : false;
    }
}
