package com.ccc.oa.service.impl;

import com.ccc.oa.dao.DepartmentDao;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        return departmentDao.deleteById(id);
    }

    @Transactional
    @Override
    public int insert(Department department) {
        if (!isExist(department)) {
            department.setDate(new java.sql.Date(new Date().getTime()));
            return departmentDao.insert(department);
        }
        return 0;
    }

    @Override
    public List<Department> selectAllDepartment() {
        return departmentDao.selectAllDepartment();
    }

    @Override
    public List<Department> selectByChildrenId(Long id) {
        return departmentDao.selectByChildrenId(id);
    }

    @Override
    public Department selectById(Long id){
        return departmentDao.selectById(id);
    }

    @Override
    public Member selectUsers(Long id) {
        return departmentDao.selectUsers(id);
    }

    @Transactional
    @Override
    public int updateById(Department department) {
        if (isExist(department)) {
            return departmentDao.updateById(department);
        }
        return 0;
    }

    private boolean isExist(Department department) {
        if (null != department) {
            Department exist = departmentDao.selectById(department.getId());
            return null != exist;
        }
        return false;
    }
}
