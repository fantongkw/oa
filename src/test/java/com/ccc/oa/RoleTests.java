package com.ccc.oa;

import com.ccc.oa.dao.UserDao;
import com.ccc.oa.model.Permission;
import com.ccc.oa.model.Role;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author: ccc
 * @Description:
 * @Date: 2018-11-28
 * @Time: 13:11
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RoleTests {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Test
    public void testSelectById(){
        Member member = userDao.selectById(5L);
        Role role = userDao.selectRole(member.getRoleId());
        List<Permission> permissions = roleService.selectPermissions(role.getId());
        permissions.forEach(System.out::println);
    }

    @Test
    public void testAddRole() {
        Role role = new Role();
        role.setId(6L);
        role.setName("test");
        int i = roleService.insertSelective(role);
        System.out.println(i);
    }

    @Test
    public void testDeleteRole() {

        int i = roleService.deleteById(7L);
        System.out.println(i);
    }
}
