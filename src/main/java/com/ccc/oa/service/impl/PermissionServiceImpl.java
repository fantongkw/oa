package com.ccc.oa.service.impl;


import com.ccc.oa.dao.PermissionDao;
import com.ccc.oa.model.Permission;
import com.ccc.oa.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        return permissionDao.insert(record);
    }

    @Override
    public int insertSelective(Permission record) {
        return permissionDao.insertSelective(record);
    }

    @Override
    public Permission selectById(Long id) {
        return permissionDao.selectById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return permissionDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionDao.updateByPrimaryKey(record);
    }
}
