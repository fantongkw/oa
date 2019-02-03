package com.ccc.oa.dao;

import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "permissionDao")
public interface PermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectAllPermission();

    Permission selectById(Long id);

    List<Role> selectRole(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}