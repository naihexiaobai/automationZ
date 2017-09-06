package com.wap.controller.dao.impdao;

import com.wap.controller.dao.FitingListMapper;
import com.wap.model.FitingList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@Service("fitingListMapperImp")
public class FitingListMapperImp implements FitingListMapper {
    @Resource(name = "fitingListMapper")
    private FitingListMapper fitingListMapper;

    public int deleteByPrimaryKey(Integer id) {
        return fitingListMapper.deleteByPrimaryKey(id);
    }

    public int insert(FitingList record) {
        return fitingListMapper.insert(record);
    }

    public List<FitingList> selectByAPId(Integer id) {
        return fitingListMapper.selectByAPId(id);
    }

    public int insertSelective(FitingList record) {
        return fitingListMapper.insertSelective(record);
    }

    public FitingList selectByPrimaryKey(Integer id) {
        return fitingListMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(FitingList record) {
        return fitingListMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(FitingList record) {
        return fitingListMapper.updateByPrimaryKey(record);
    }
}
