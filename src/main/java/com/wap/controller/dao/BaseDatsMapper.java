package com.wap.controller.dao;

import com.wap.model.BaseDats;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("baseDatsMapper")
public interface BaseDatsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseDats record);

    List<BaseDats> selectByLikeLevelCode(String levelcode);

    /**
     * 通过父编码查询
     *
     * @param levelcode
     * @return
     */
    List<BaseDats> selectByFatherLevelCode(String levelcode);

    /**
     * 查询所有父目录
     *
     * @return
     */
    List<BaseDats> selectByNotLikeLevelCode();

    /**
     * 通过简称模糊查找
     *
     * @param sn
     * @return
     */
    List<BaseDats> selectByS(String sn);

    /**
     * 通过全称模糊查找
     *
     * @param fn
     * @return
     */
    List<BaseDats> selectByF(String fn);

    /**
     * 通过编码查找
     *
     * @param levelcode
     * @return
     */
    List<BaseDats> selectByLevelCode(String levelcode);

    /**
     * 通过型号模糊查找
     *
     * @param sp
     * @return
     */
    List<BaseDats> selectBySp(String sp);

    int insertSelective(BaseDats record);

    BaseDats selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseDats record);

    int updateByPrimaryKey(BaseDats record);
}