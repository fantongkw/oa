package com.ccc.oa.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleHasPermissionDao")
public interface RoleHasPermissionDao {
    int deleteById(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int deleteByPermissionId(@Param("permissionId") Long permissionId);

    int insertPermissions(@Param("roleId") Long roleId, @Param("list") List<Long> permissionId);

    int insertRoles(@Param("roleId") List<Long> roleId, @Param("list") Long permissionId);

}