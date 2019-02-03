package com.ccc.oa.service.impl;

import com.ccc.oa.dao.RoleHasPermissionDao;
import com.ccc.oa.service.RoleHasPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleHasPermissionService")
public class RoleHasPermissionServiceImpl implements RoleHasPermissionService {

    @Autowired
    private RoleHasPermissionDao roleHasPermissionDao;

    @Override
    public int deleteByPrimaryKey(Long roleId, Long permissionId) {
        return roleHasPermissionDao.deleteByPrimaryKey(roleId, permissionId);
    }

    @Override
    public int insert(Long roleId, List<Long> permissionId) {
        return roleHasPermissionDao.insert(roleId, permissionId);
    }
}
