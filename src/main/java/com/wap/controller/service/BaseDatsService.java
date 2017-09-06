package com.wap.controller.service;

import com.wap.controller.dao.impdao.BaseDatsMapperImp;
import com.wap.model.BaseDats;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/28.
 */
@Service("baseDatsService")
public class BaseDatsService {
    @Resource(name = "baseDatsMapperImp")
    private BaseDatsMapperImp baseDatsMapperImp;

    public List<BaseDats> selectByLikeLevelCode(String levelcode) {
        return baseDatsMapperImp.selectByLikeLevelCode(levelcode);
    }

    public BaseDats selectByPrimaryKey(Integer id) {
        return baseDatsMapperImp.selectByPrimaryKey(id);
    }

    public List<BaseDats> selectByFatherLevelCode(String levelcode) {
        return baseDatsMapperImp.selectByFatherLevelCode(levelcode);
    }

    public List<BaseDats> selectByNotLikeLevelCode() {
        return baseDatsMapperImp.selectByNotLikeLevelCode();
    }

    public int insertSelective(BaseDats record) {
        return baseDatsMapperImp.insertSelective(record);
    }

    public List<BaseDats> selectByS(String sn) {
        return baseDatsMapperImp.selectByS(sn);
    }

    public List<BaseDats> selectByLevelCode(String levelcode) {
        return baseDatsMapperImp.selectByLevelCode(levelcode);
    }

    public List<BaseDats> selectByF(String fn) {
        return baseDatsMapperImp.selectByF(fn);
    }

    public List<BaseDats> selectBySp(String sp) {
        return baseDatsMapperImp.selectBySp(sp);
    }
}
