package com.ccc.oa.service;

import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;

import java.util.List;

public interface PermissionService {
    int deleteById(Long id);

    int insert(Permission permission);

    Permission selectById(Long id);

    List<Permission> selectAllPermission();

    List<Role> selectRoles(Long id);

    int updateById(Permission permission);

}
