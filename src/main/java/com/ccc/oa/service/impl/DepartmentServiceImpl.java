package com.ccc.oa.service.impl;

import com.ccc.oa.dao.DepartmentDao;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOG = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        LOG.info("Department " + id + " Deleted Success");
        return departmentDao.deleteById(id);
    }

    @Transactional
    @Override
    public int insert(Department department) {
        if (!isExist(department)) {
            department.setDate(new java.sql.Date(new Date().getTime()));
            LOG.info("Department " + department.getId() + " Added Success");
            return departmentDao.insert(department);
        }
        return 0;
    }

    @Override
    public List<Department> selectAllDepartment() {
        return departmentDao.selectAllDepartment();
    }

    @Override
    public List<Department> selectChildren(Long id) {
        return departmentDao.selectChildren(id);
    }

    @Override
    public Department selectById(Long id) {
        return departmentDao.selectById(id);
    }

    @Override
    public List<Member> selectUsers(Long id) {
        return departmentDao.selectUsers(id);
    }

    @Transactional
    @Override
    public int updateById(Department department) {
        if (isExist(department)) {
            LOG.info("Department " + department.getId() + " Updated Success");
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
