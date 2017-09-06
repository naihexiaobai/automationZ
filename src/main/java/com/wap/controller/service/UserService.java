package com.wap.controller.service;

import com.wap.controller.dao.impdao.UserMapperImp;
import com.wap.model.User;
import com.wap.util.PagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/24.
 */
@Service("userService")
public class UserService {
    @Resource(name = "userMapperImp")
    private UserMapperImp userMapperImp;

    public User getUserById(Integer id) {
        User user = userMapperImp.selectByPrimaryKey(id);
        return user;
    }

    public User getUserByUserName(String userNmae) {
        return userMapperImp.selectByUserName(userNmae);
    }

    public List<User> getUserPositionCode() {
        return userMapperImp.selectGroupByPositionCode();
    }

    public int selectCountId() {
        return userMapperImp.selectCountId();
    }

    public User selectByPrimaryKey(Integer id) {
        return userMapperImp.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User record) {
        return userMapperImp.updateByPrimaryKeySelective(record);
    }

    public int insertSelective(User record) {
        return userMapperImp.insertSelective(record);
    }

    public List<User> selectByPage(PagerUtil pagerUtil) {
        return userMapperImp.selectByPage(pagerUtil);
    }
}
