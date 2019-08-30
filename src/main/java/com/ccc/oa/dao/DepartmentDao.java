package com.ccc.oa.dao;

import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository(value = "DepartmentDao")
public interface DepartmentDao {
    int deleteById(@Param("id") Long id);

    int insert(Department department);

    List<Department> selectAllDepartment();

    List<Department> selectByChildrenId(@Param("id") Long id);

    Department selectById(@Param("id") Long id);

    Member selectUsers(@Param("id") Long id);

    int updateById(Department department);

}