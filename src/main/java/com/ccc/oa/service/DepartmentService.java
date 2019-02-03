package com.ccc.oa.service;

import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;

import java.util.List;

public interface DepartmentService {
    int deleteById(Long id);

    int insert(Department department);

    int insertSelective(Department department);

    List<Department> selectAllDept();

    List<Department> selectByCid(Long id);

    Department selectById(Long id);

    Member selectUsers(Long id);

    int updateByIdSelective(Department department);

    int updateById(Department department);
}
