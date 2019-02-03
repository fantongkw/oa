package com.ccc.oa.dao;

import com.ccc.oa.model.Member;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "roleDao")
public interface RoleDao {
    int deleteById(@Param("id") Long id);

    int insert(Role role);

    int insertSelective(Role role);

    List<Role> selectAllRole();

    Role selectById(@Param("id") Long id);

    List<Member> selectUsers(@Param("id") Long id);

    List<Permission> selectPermissions(@Param("roleId") Long roleId);

    int updateByIdSelective(Role role);

    int updateById(Role role);
}