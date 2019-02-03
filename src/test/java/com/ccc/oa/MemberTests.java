package com.ccc.oa;

import com.ccc.oa.dao.RoleDao;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testSelectById(){
        Member list = userService.selectById(1L);
        System.out.println(list.getDepartment().getName());
    }

    @Test
    public void testSelectDept() {
        Department department = userService.selectDept(1L);
        System.out.println(department);
    }

    @Test
    public void testSaveUser(){
        Member member = new Member();
        member.setName("vasd");
        member.setUsername("asd");
        member.setPassword("sadas");
        userService.insertSelective(member);
    }

    @Test
    public void testDelUser(){
        userService.deleteById(7L);
    }
}
