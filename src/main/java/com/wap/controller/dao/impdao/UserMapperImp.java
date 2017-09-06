package com.wap.controller.dao.impdao;

import com.wap.controller.dao.UserMapper;
import com.wap.model.User;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/24.
 */
@Service("userMapperImp")
public class UserMapperImp implements UserMapper {
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User record) {
        return userMapper.insert(record);
    }

    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    public int selectCountId() {
        return userMapper.selectCountId();
    }

    public List<User> selectByPage(PagerUtil pagerUtil) {
        return userMapper.selectByPage(pagerUtil);
    }

    public List<User> selectGroupByPositionCode() {
        return userMapper.selectGroupByPositionCode();
    }

    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
