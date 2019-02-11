package com.ccc.oa.controller;

import com.ccc.oa.Exception.CustomException;
import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.DepartmentService;
import com.ccc.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/user_delete/{id}")
    public String delete(@PathVariable Long id) {
        int result = userService.deleteById(id);
        if (result == 1) {
            return "redirect:/user/user_list";
        }
        throw new CustomException("部门删除失败");
    }

    @GetMapping(value = "/user_add")
    public String add(Model model){
        List<Member> objects = userService.selectAllUser();
        List<Department> departments = departmentService.selectAllDept();
        model.addAttribute("objects", objects);
        model.addAttribute("departments", departments);
        return "/user/user_add";
    }

    @PostMapping(value = "/user_added")
    public String added(@Validated Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int success = userService.insertSelective(member);
        if (success == 1) {
            return "redirect:/user/user_list";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "服务器繁忙，请稍后重试");
            return "/user/user_add";
        }
    }

    @GetMapping(value = "/user_update")
    public String update(Model model) {
        Member objectId = new Member();
        model.addAttribute("objectId", objectId);
        List<Member> objects = userService.selectAllUser();
        model.addAttribute("objects", objects);
        return "/user/user_update";
    }

    @GetMapping(value = "/user_update/{id}")
    public String updateById(@PathVariable Long id, Model model) {
        if (id != null) {
            Member objectId = userService.selectById(id);
            model.addAttribute("objectId", objectId);
        }
        List<Member> objects = userService.selectAllUser();
        model.addAttribute("objects", objects);
        return "/user/user_update";
    }

    @PostMapping(value = "/user_updated")
    public String updated(Member member, Model model) {
        int success = userService.updateByIdSelective(member);
        if (success == 1) {
            return "redirect:/user/user_list";
        }else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "服务器繁忙，请稍后重试");
            return "/user/user_update";
        }
    }
}
