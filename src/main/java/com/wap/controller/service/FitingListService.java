package com.wap.controller.service;

import com.wap.controller.dao.impdao.FitingListMapperImp;
import com.wap.model.FitingList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@Service("fitingListService")
public class FitingListService {
    @Resource(name = "fitingListMapperImp")
    private FitingListMapperImp fitingListMapperImp;

    public List<FitingList> selectByAPId(Integer id) {
        return fitingListMapperImp.selectByAPId(id);
    }

    public int updateByPrimaryKeySelective(FitingList record) {
        return fitingListMapperImp.updateByPrimaryKeySelective(record);
    }

    public int insertSelective(FitingList record) {
        return fitingListMapperImp.insertSelective(record);
    }

    public FitingList selectByPrimaryKey(Integer id) {
        return fitingListMapperImp.selectByPrimaryKey(id);
    }

    public int deleteByPrimaryKey(Integer id) {
        return fitingListMapperImp.deleteByPrimaryKey(id);
    }
}
