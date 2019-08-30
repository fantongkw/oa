package com.ccc.oa.service.impl;


import com.ccc.oa.dao.PermissionDao;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDao permissionDao;

    @Autowired
    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return permissionDao.deleteById(id);
    }

    @Transactional
    @Override
    public int insert(Permission permission) {
        return permissionDao.insert(permission);
    }

    @Override
    public Permission selectById(Long id) {
        return permissionDao.selectById(id);
    }

    @Override
    public List<Permission> selectAllPermission() {
        return permissionDao.selectAllPermission();
    }

    @Override
    public List<Role> selectRoles(Long id) {
        return permissionDao.selectRoles(id);
    }

    @Transactional
    @Override
    public int updateById(Permission permission) {
        return permissionDao.updateById(permission);
    }
}
