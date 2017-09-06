package com.wap.controller.dao;

import com.wap.model.AutoProduct;
import com.wap.model.AutoProductSelectPage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("autoProductMapper")
public interface AutoProductMapper {
    int deleteByPrimaryKey(Integer id);

    int selectByApCount(AutoProductSelectPage autoProduct);

    List<AutoProduct> selectByAp(AutoProductSelectPage autoProduct);

    int insert(AutoProduct record);

    int insertSelective(AutoProduct record);

    AutoProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AutoProduct record);

    int updateByPrimaryKey(AutoProduct record);
}