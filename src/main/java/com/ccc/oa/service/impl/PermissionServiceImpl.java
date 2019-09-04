package com.ccc.oa.service.impl;


import com.ccc.oa.dao.PermissionDao;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionServiceImpl.class);

    private final PermissionDao permissionDao;

    @Autowired
    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        LOG.info("Permission "+ id +" Deleted Success");
        return permissionDao.deleteById(id);
    }

    @Transactional
    @Override
    public int insert(Permission permission) {
        LOG.info("Permission "+ permission.getId() +" Added Success");
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
        LOG.info("Permission "+ permission.getId() +" Updated Success");
        return permissionDao.updateById(permission);
    }
}
