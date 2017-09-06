package com.wap.controller.dao.impdao;

import com.wap.controller.dao.BaseDatsMapper;
import com.wap.model.BaseDats;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/28.
 */
@Service("baseDatsMapperImp")
public class BaseDatsMapperImp implements BaseDatsMapper {
    @Resource(name = "baseDatsMapper")
    private BaseDatsMapper baseDatsMapper;

    public int deleteByPrimaryKey(Integer id) {
        return baseDatsMapper.deleteByPrimaryKey(id);
    }

    public int insert(BaseDats record) {
        return baseDatsMapper.insert(record);
    }

    public List<BaseDats> selectByLikeLevelCode(String levelcode) {
        return baseDatsMapper.selectByLikeLevelCode(levelcode);
    }

    public List<BaseDats> selectByFatherLevelCode(String levelcode) {
        return baseDatsMapper.selectByFatherLevelCode(levelcode);
    }

    public List<BaseDats> selectByNotLikeLevelCode() {
        return baseDatsMapper.selectByNotLikeLevelCode();
    }

    public List<BaseDats> selectByS(String sn) {
        return baseDatsMapper.selectByS(sn);
    }

    public List<BaseDats> selectByF(String fn) {
        return baseDatsMapper.selectByF(fn);
    }

    public List<BaseDats> selectByLevelCode(String levelcode) {
        return baseDatsMapper.selectByLevelCode(levelcode);
    }

    public List<BaseDats> selectBySp(String sp) {
        return baseDatsMapper.selectBySp(sp);
    }

    public int insertSelective(BaseDats record) {
        return baseDatsMapper.insertSelective(record);
    }

    public BaseDats selectByPrimaryKey(Integer id) {
        return baseDatsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BaseDats record) {
        return baseDatsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BaseDats record) {
        return baseDatsMapper.updateByPrimaryKey(record);
    }
}
