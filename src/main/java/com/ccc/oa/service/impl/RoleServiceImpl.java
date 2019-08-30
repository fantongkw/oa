package com.ccc.oa.service.impl;

import com.ccc.oa.dao.RoleDao;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return roleDao.deleteById(id);
    }

    @Transactional
    @Override
    public int insert(Role role) {
        if (!isExist(role)) {
            return roleDao.insert(role);
        }
        return 0;
    }

    @Override
    public Role selectById(Long id) {
        return roleDao.selectById(id);
    }

    @Override
    public List<Role> selectAllRole() {
        return roleDao.selectAllRole();
    }

    @Override
    public List<Member> selectUsers(Long id) {
        return roleDao.selectUsers(id);
    }

    @Override
    public List<Permission> selectPermissions(Long roleId) {
        return roleDao.selectPermissions(roleId);
    }

    @Transactional
    @Override
    public int updateById(Role role) {
        if (isExist(role)) {
            return roleDao.updateById(role);
        }
        return 0;
    }

    private boolean isExist(Role role) {
        if (null != role) {
            Role exist = roleDao.selectById(role.getId());
            return null != exist;
        }
        return false;
    }
}
