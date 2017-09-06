package com.wap.controller.dao;

import com.wap.model.User;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    int selectCountId();

    List<User> selectByPage(PagerUtil pagerUtil);

    List<User> selectGroupByPositionCode();

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}