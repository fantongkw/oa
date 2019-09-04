package com.ccc.oa;

import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.DepartmentService;
import com.ccc.oa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSelectById(){
        Member list = userService.selectById(1L);
        System.out.println(list);
    }

    @Test
    public void testDeleteById() {
        int i = userService.deleteById(80L);
        System.out.println(i);
    }

    @Test
    public void testSelectRole(){
        Member list = userService.selectById(11L);
        Role role = userService.selectRole(list.getRoleId());
        System.out.println(role);
    }

    @Test
    public void testSelectDept() {
        Department department = userService.selectDepartment(1L);
        System.out.println(department);
    }

    @Test
    public void testSaveUser(){
        Member member = new Member();
        member.setName("vasd");
        member.setUsername("aaaaaa");
        member.setPassword("sadas");
        userService.insert(member);
    }

    @Test
    public void testDelUser(){
        userService.deleteById(7L);
    }

    @Test
    public void testInsert() {
        Member member = new Member();
        member.setUsername("aasd");
        member.setRoleId(6L);
        member.setPassword("12345678");
        member.setAvatar("/images/faces-clipart/default.jpg");
        Date date = new Date(new java.util.Date().getTime());
        System.out.println(date);
        member.setDate(date);
        userService.insert(member);
    }

    @Test
    public void testUpdate() {
        Member member = userService.selectById(80L);
        if (member != null) {
            member.setAvatar("");
        }
        int i = userService.updateById(member);
        System.out.println(i);
    }
}
