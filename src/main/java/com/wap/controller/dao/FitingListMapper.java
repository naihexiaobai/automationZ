package com.wap.controller.dao;

import com.wap.model.FitingList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fitingListMapper")
public interface FitingListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FitingList record);

    List<FitingList> selectByAPId(Integer id);

    int insertSelective(FitingList record);

    FitingList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FitingList record);

    int updateByPrimaryKey(FitingList record);
}