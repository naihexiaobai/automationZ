package com.wap.controller.dao;

import com.wap.model.MenuTree;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menuTreeMapper")
public interface MenuTreeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuTree record);

    List<MenuTree> selectAll();

    List<MenuTree> selectAllStatusByPage(PagerUtil pagerUtil);

    int selectAllStatusCount();

    List<MenuTree> selectGruopByLC(String listC);

    List<MenuTree> selectByLFC(String listFC);

    void selectByIdUpdateStatus(MenuTree menuTree);

    List<MenuTree> selectMenuByListCode(String listcode);

    List<MenuTree> selectMenuInId(String ids);

    int insertSelective(MenuTree record);

    MenuTree selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuTree record);

    int updateByPrimaryKey(MenuTree record);
}