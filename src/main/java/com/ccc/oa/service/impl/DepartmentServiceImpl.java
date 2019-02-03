package com.ccc.oa.service.impl;

import com.ccc.oa.dao.DepartmentDao;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public int deleteById(Long id) {
        Department department = departmentDao.selectById(id);
        if (department != null) {
            return departmentDao.deleteById(id);
        }
        return 0;
    }

    @Override
    public int insert(Department department) {
        return departmentDao.insert(department);
    }

    @Override
    public int insertSelective(Department department) {
        Department duplicate = departmentDao.selectById(department.getId());
        if (duplicate == null) {
            department.setDate(new java.sql.Date(new Date().getTime()));
            return departmentDao.insertSelective(department);
        }
        return 0;
    }

    @Override
    public List<Department> selectAllDept() {
        return departmentDao.selectAllDept();
    }

    @Override
    public List<Department> selectByCid(Long id) {
        return departmentDao.selectByCid(id);
    }

    @Override
    public Department selectById(Long id){
        return departmentDao.selectById(id);
    }

    @Override
    public Member selectUsers(Long id) {
        return departmentDao.selectUsers(id);
    }

    @Override
    public int updateByIdSelective(Department department) {
        return departmentDao.updateByIdSelective(department);
    }

    @Override
    public int updateById(Department department) {
        return departmentDao.updateById(department);
    }
}
