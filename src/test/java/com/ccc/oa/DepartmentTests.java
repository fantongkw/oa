package com.ccc.oa;

import com.ccc.oa.dao.DepartmentDao;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author: ccc
 * @Description:
 * @Date: 2018-11-28
 * @Time: 13:11
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentTests {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void testSelectAll() {
        int size = departmentDao.selectAllDepartment().size();
        System.out.println(size);
    }

    @Test
    public void testSelectById() {
        Department department = departmentDao.selectById(2L);
        /*System.out.println(department);*/
        System.out.println(department.getChildren().size());
        department.getChildren().forEach(System.out::println);
    }

    @Test
    public void testSelectUsers() {
        List<Member> member = departmentDao.selectUsers(2L);
        member.forEach(e -> System.out.println(e.getDepartmentId()));
    }

    @Test
    public void testInsert() {
        Department department = new Department();
        department.setName("aaa");
        department.setDescription("aaa");
        department.setDate(new Date(new java.util.Date().getTime()));
        int result = departmentDao.insert(department);
        System.out.println(result);
    }
}
