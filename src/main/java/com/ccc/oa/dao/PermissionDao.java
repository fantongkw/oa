package com.ccc.oa.dao;

import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "permissionDao")
public interface PermissionDao {
    int deleteById(@Param("id") Long id);

    int insert(Permission permission);

    List<Permission> selectAllPermission();

    Permission selectById(@Param("id") Long id);

    List<Role> selectRoles(@Param("id") Long id);

    int updateById(Permission permission);

}