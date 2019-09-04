package com.ccc.oa.controller;

import com.ccc.oa.Exception.CustomException;
import com.ccc.oa.model.Department;
import com.ccc.oa.service.DepartmentService;
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
@RequestMapping(value = "/dept")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/dept_list")
    public String list(Model model){
        Department department = new Department();
        List<Department> objects = departmentService.selectAllDepartment();
        objects.forEach(dept -> {
            if (null == dept.getParent()) {
                dept.setParent(department);
            }
        });
        model.addAttribute("objects", objects);
        return "/dept/dept_list";
    }

    @PreAuthorize("hasRole('ROLE_DEPTS')")
    @PostMapping(value = "/dept_delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable Long id){
        int queryResult = departmentService.deleteById(id);
        return queryResult == 1;
    }

    @GetMapping(value = "/dept_add")
    public String add(){
        return "/dept/dept_add";
    }


    @PreAuthorize("hasRole('ROLE_DEPTS')")
    @PostMapping(value = "/dept_added")
    public String added(@Validated Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int queryResult = departmentService.insert(department);
        if (queryResult == 1) {
            return "redirect:/dept/dept_list";
        }else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "部门添加失败");
            return "/dept/dept_add";
        }
    }

    @GetMapping(value = "/dept_update")
    public String update(Model model){
        Department objectId = new Department();
        model.addAttribute("objectId", objectId);
        List<Department> objects = departmentService.selectAllDepartment();
        model.addAttribute("objects", objects);
        return "/dept/dept_update";
    }

    @GetMapping(value = "/dept_update/{id}")
    public String updateById(@PathVariable Long id, Model model){
        if (id != null) {
            Department objectId = departmentService.selectById(id);
            model.addAttribute("objectId", objectId);
        }
        List<Department> objects = departmentService.selectAllDepartment();
        model.addAttribute("objects", objects);
        return "/dept/dept_update";
    }

    @PreAuthorize("hasRole('ROLE_DEPTS')")
    @PostMapping(value = "/dept_updated")
    public String updated(@Validated Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int queryResult = departmentService.updateById(department);
        if (queryResult == 1) {
            return "redirect:/dept/dept_list";
        }else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "部门更新失败");
            return "/dept/dept_update";
        }
    }
}
