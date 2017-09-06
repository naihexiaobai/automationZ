package com.wap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wap.controller.service.PositionService;
import com.wap.model.Position;
import com.wap.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/7/26.
 */
@Controller
@RequestMapping("positionCc")
public class PositionCc extends PublicFunction {
    @Resource(name = "positionService")
    private PositionService positionService;

    @RequestMapping("getAllPos")
    @ResponseBody
    public JSONArray getAllPos(HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        List<Position> positions = positionService.getAll();
        for (Position position : positions) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", position.getId());
            jsonObject.put("position", position.getPosition());
            jsonObject.put("status", position.getStatus());
            jsonObject.put("positioncode", position.getPositioncode());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @RequestMapping("updatePos")
    @ResponseBody
    @Transactional
    public boolean updatePos(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (isStringNull(id)) {
            return false;
        }
        Position position = positionService.selectByPrimaryKey(Integer.valueOf(id));
        if (position == null) {
            return false;
        } else {
            if (position.getStatus() == 1) {
                position.setStatus(Integer.valueOf("0").byteValue());
            } else {
                position.setStatus(Integer.valueOf("1").byteValue());
            }
        }
        int q = positionService.updateByPositionCodeSelective(position);
        return q > 0 ? true : false;
    }

    @RequestMapping("addPos")
    @ResponseBody
    public boolean addPos(HttpServletRequest request) {
        String position = request.getParameter("position");
        String positioncode = request.getParameter("positioncode");
        byte positioncodeb = stringToByte(positioncode);
        if (positioncodeb < 0 || isStringNull(position)) {
            return false;
        }
        Position position1 = new Position();
        //权限开闭状态，1打开，0关闭
        position1.setStatus(stringToByte("1"));
        position1.setPositioncode(positioncodeb);
        position1.setPosition(position);
        position1.setCreatetime(DateUtil.getDateFormat(new Date(), DateUtil.DATETIME_DEFAULT_FORMAT));
        int q = positionService.insertSelective(position1);
        return q > 0 ? true : false;
    }
}
