package com.ccc.oa.service.impl;

import com.ccc.oa.dao.RoleHasPermissionDao;
import com.ccc.oa.service.RoleHasPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleHasPermissionService")
public class RoleHasPermissionServiceImpl implements RoleHasPermissionService {

    private final RoleHasPermissionDao roleHasPermissionDao;

    public RoleHasPermissionServiceImpl(RoleHasPermissionDao roleHasPermissionDao) {
        this.roleHasPermissionDao = roleHasPermissionDao;
    }

    @Override
    public int deleteById(Long roleId, Long permissionId) {
        return roleHasPermissionDao.deleteById(roleId, permissionId);
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        return roleHasPermissionDao.deleteByRoleId(roleId);
    }

    @Override
    public int deleteByPermissionId(Long permissionId) {
        return roleHasPermissionDao.deleteByPermissionId(permissionId);
    }

    @Override
    public int insertPermissions(Long roleId, List<Long> permissionId) {
        return roleHasPermissionDao.insertPermissions(roleId, permissionId);
    }

    @Override
    public int insertRoles(List<Long> roleId, Long permissionId) {
        return roleHasPermissionDao.insertRoles(roleId, permissionId);
    }
}
