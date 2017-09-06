package com.wap.controller.dao;

import com.wap.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("permissionMapper")
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertList(List<Permission> permissions);

    int deleteByPositionCode(Integer positioncode);

    List<Permission> selectByPositionCode(Integer positioncode);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}