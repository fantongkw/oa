package com.ccc.oa.controller;

import com.ccc.oa.Exception.CustomException;
import com.ccc.oa.model.Role;
import com.ccc.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/role_list")
    public String list(Model model) {
        model.addAttribute("objects", roleService.selectAllRole());
        return "/role/role_list";
    }

    @PreAuthorize("hasRole('ROLE_ROLES')")
    @PostMapping(value = "/role_delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable Long id) {
        int queryResult = roleService.deleteById(id);
        return queryResult == 1;
    }

    @GetMapping(value = "/role_add")
    public String add() {
        return "/role/role_add";
    }

    @PreAuthorize("hasRole('ROLE_ROLES')")
    @PostMapping(value = "/role_added")
    public String added(@Validated Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int queryResult = roleService.insert(role);
        if (queryResult == 1) {
            return "redirect:/role/role_list";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "角色添加失败");
            return "/role/role_add";
        }
    }

    @GetMapping(value = "/role_update")
    public String update(Model model) {
        Role objectId = new Role();
        model.addAttribute("objectId", objectId);
        List<Role> objects = roleService.selectAllRole();
        model.addAttribute("objects", objects);
        return "/role/role_update";
    }

    @GetMapping(value = "/role_update/{id}")
    public String updateById(@PathVariable Long id, Model model) {
        if (null != id) {
            Role objectId = roleService.selectById(id);
            model.addAttribute("objectId", objectId);
        }
        List<Role> objects = roleService.selectAllRole();
        model.addAttribute("objects", objects);
        return "/role/role_update";
    }

    @PreAuthorize("hasRole('ROLE_ROLES')")
    @PostMapping(value = "/role_updated")
    public String updated(@Validated Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int queryResult = roleService.updateById(role);
        if (queryResult == 1) {
            return "redirect:/role/role_list";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "角色更新失败");
            return "/role/role_update";
        }
    }
}
