package com.ccc.oa.service.impl;

import com.ccc.oa.dao.RoleDao;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Override
    public int deleteById(Long id) {
        Role role = roleDao.selectById(id);
        if (role != null) {
            return roleDao.deleteById(id);
        }
        return 0;
    }

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public int insert(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public int insertSelective(Role role) {
        Role duplicate = roleDao.selectById(role.getId());
        if (duplicate == null) {
            return roleDao.insertSelective(role);
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

    @Override
    public int updateByIdSelective(Role role) {
        return roleDao.updateByIdSelective(role);
    }

    @Override
    public int updateById(Role role) {
        return roleDao.updateById(role);
    }
}
