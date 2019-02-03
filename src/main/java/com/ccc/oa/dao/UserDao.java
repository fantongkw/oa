package com.ccc.oa.dao;

import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userDao")
public interface UserDao {
    int deleteById(@Param("id") Long id);

    int insert(Member member);

    int insertSelective(Member member);

    List<Member> selectAllUser();

    Member selectById(@Param("id") Long id);

    Member loadUserByUsername(String username);

    Member selectByEmail(String email);

    Department selectDept(@Param("departmentId") Long departmentId);

    Role selectRole(@Param("roleId") Long roleId);

    int updateByIdSelective(Member member);

    int updateById(Member member);
}