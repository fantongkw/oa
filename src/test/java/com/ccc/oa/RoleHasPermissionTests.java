package com.ccc.oa;

import com.ccc.oa.service.RoleHasPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleHasPermissionTests {

    @Autowired
    private RoleHasPermissionService roleHasPermissionService;

    @Test
    public void testDeleteById() {
        roleHasPermissionService.deleteById(1L, 1L);
    }

    @Test
    public void testInsertPermissions() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        roleHasPermissionService.insertPermissions(1L, list);
    }

    @Test
    public void testInsertRoles() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        roleHasPermissionService.insertRoles(list, 1L);
    }
}
