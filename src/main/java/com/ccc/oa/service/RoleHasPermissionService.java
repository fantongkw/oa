package com.ccc.oa.service;

import java.util.List;

public interface RoleHasPermissionService {
    int deleteByPrimaryKey(Long roleId, Long permissionId);

    int insert(Long roleId, List<Long> permissionId);
}
