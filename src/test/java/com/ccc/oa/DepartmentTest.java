package com.ccc.oa;

import com.ccc.oa.dao.DepartmentDao;
import com.ccc.oa.model.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Created with IntelliJ IDEA.
 * @Author: ccc
 * @Description:
 * @Date: 2018-11-28
 * @Time: 13:11
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DepartmentTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void testSelectById(){
        Department department = departmentDao.selectById(1L);
        System.out.println(department);

    }
}
