package com.ccc.oa.service;

import com.ccc.oa.model.Department;
import com.ccc.oa.model.Role;
import com.ccc.oa.model.Member;

import java.util.List;

public interface UserService {
    int deleteById(Long id);

    int insert(Member member);

    int insertSelective(Member member);

    List<Member> selectAllUser();

    Member selectById(Long id);

    Member loadUserByUsername(String username);

    Member selectByEmail(String email);

    Role selectRole(Long roleId);

    Department selectDept(Long departmentId);

    int updateByIdSelective(Member member);

}
