package com.ccc.oa.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleHasPermissionDao")
public interface RoleHasPermissionDao {
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    int insert(@Param("roleId") Long roleId, @Param("list") List<Long> permissionId);

}