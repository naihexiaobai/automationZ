package com.wap.controller.service;

import com.wap.controller.dao.impdao.PermissionMapperImp;
import com.wap.model.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/25.
 */
@Service("permissionService")
public class PermissionService {
    @Resource(name = "permissionMapperImp")
    private PermissionMapperImp permissionMapperImp;

    public List<Permission> selectByPositionCode(Integer positioncode) {
        return permissionMapperImp.selectByPositionCode(positioncode);
    }

    public int insertList(List<Permission> permissions) {
        return permissionMapperImp.insertList(permissions);
    }

    public int deleteByPositionCode(Integer positioncode) {
        return permissionMapperImp.deleteByPositionCode(positioncode);
    }

    public int insertP(Permission permission) {
        return permissionMapperImp.insert(permission);
    }
}
