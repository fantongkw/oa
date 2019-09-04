package com.ccc.oa.controller;

import com.ccc.oa.Exception.CustomException;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.DepartmentService;
import com.ccc.oa.service.UserService;
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
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    public UserController(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/user_list")
    public String list(Model model){
        model.addAttribute("objects", userService.selectAllUser());
        return "/user/user_list";
    }

    @PreAuthorize("hasRole('ROLE_USERS')")
    @PostMapping(value = "/user_delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable Long id) {
        int queryResult = userService.deleteById(id);
        return queryResult == 1;
    }

    @GetMapping(value = "/user_add")
    public String add(Model model){
        List<Member> objects = userService.selectAllUser();
        List<Department> departments = departmentService.selectAllDepartment();
        model.addAttribute("objects", objects);
        model.addAttribute("departments", departments);
        return "/user/user_add";
    }

    @PreAuthorize("hasRole('ROLE_USERS')")
    @PostMapping(value = "/user_added")
    public String added(@Validated Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int queryResult = userService.insert(member);
        if (queryResult == 1) {
            return "redirect:/user/user_list";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "员工添加失败");
            return "/user/user_add";
        }
    }

    @GetMapping(value = "/user_update")
    public String update(Model model) {
        Member objectId = new Member();
        model.addAttribute("objectId", objectId);
        List<Member> objects = userService.selectAllUser();
        model.addAttribute("objects", objects);
        List<Department> departments = departmentService.selectAllDepartment();
        model.addAttribute("departments", departments);
        return "/user/user_update";
    }

    @GetMapping(value = "/user_update/{id}")
    public String updateById(@PathVariable Long id, Model model) {
        if (null != id) {
            Member objectId = userService.selectById(id);
            model.addAttribute("objectId", objectId);
        }
        List<Department> departments = departmentService.selectAllDepartment();
        model.addAttribute("departments", departments);
        List<Member> objects = userService.selectAllUser();
        model.addAttribute("objects", objects);
        return "/user/user_update";
    }

    @PreAuthorize("hasRole('ROLE_USERS')")
    @PostMapping(value = "/user_updated")
    public String updated(Member member, Model model) {
        int queryResult = userService.updateById(member);
        if (queryResult == 1) {
            return "redirect:/user/user_list";
        }else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "员工更新失败");
            return "/user/user_update";
        }
    }
}
