package com.wap.controller.dao;

import com.wap.model.ImageAboutAp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imageAboutApMapper")
public interface ImageAboutApMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImageAboutAp record);

    int insertSelective(ImageAboutAp record);

    ImageAboutAp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageAboutAp record);

    int updateByPrimaryKey(ImageAboutAp record);

    int updateByApidSelective(ImageAboutAp imageAboutAp);

    List<ImageAboutAp> selectByApid(Integer apid);
}