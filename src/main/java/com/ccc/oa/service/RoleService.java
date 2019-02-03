package com.ccc.oa.service;

import com.ccc.oa.model.Member;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;

import java.util.List;

public interface RoleService {
    int deleteById(Long id);

    int insert(Role role);

    int insertSelective(Role role);

    List<Role> selectAllRole();

    Role selectById(Long id);

    List<Member> selectUsers(Long id);

    List<Permission> selectPermissions(Long roleId);

    int updateByIdSelective(Role role);

    int updateById(Role role);

}
