package com.ccc.oa.service;

import java.util.List;

public interface RoleHasPermissionService {
    int deleteById(Long roleId, Long permissionId);

    int deleteByRoleId(Long roleId);

    int deleteByPermissionId(Long permissionId);

    int insertPermissions(Long roleId, List<Long> permissionId);

    int insertRoles(List<Long> roleId, Long permissionId);
}
