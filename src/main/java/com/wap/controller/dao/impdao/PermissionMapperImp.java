package com.wap.controller.dao.impdao;

import com.wap.controller.dao.PermissionMapper;
import com.wap.model.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/25.
 */
@Service("permissionMapperImp")
public class PermissionMapperImp implements PermissionMapper {
    @Resource(name = "permissionMapper")
    private PermissionMapper permissionMapper;

    public int deleteByPrimaryKey(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    public int insertList(List<Permission> permissions) {
        return permissionMapper.insertList(permissions);
    }

    public int deleteByPositionCode(Integer positioncode) {
        return permissionMapper.deleteByPositionCode(positioncode);
    }

    public List<Permission> selectByPositionCode(Integer positioncode) {
        return permissionMapper.selectByPositionCode(positioncode);
    }

    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    public Permission selectByPrimaryKey(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }
}
