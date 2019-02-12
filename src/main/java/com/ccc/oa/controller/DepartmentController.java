package com.ccc.oa.controller;

import com.ccc.oa.Exception.CustomException;
import com.ccc.oa.model.Department;
import com.ccc.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Department> objects = departmentService.selectAllDept();
        for (Department dept : objects) {
            if (dept.getParent() == null) {
                dept.setParent(department);
            }
        }
        model.addAttribute("objects", objects);
        return "/dept/dept_list";
    }

    @GetMapping(value = "/dept_delete/{id}")
    public String delete(@PathVariable Long id){
        int queryResult = departmentService.deleteById(id);
        if (queryResult == 1) {
            return "redirect:/dept/dept_list";
        }
        throw new CustomException("部门删除失败");
    }

    @GetMapping(value = "/dept_add")
    public String add(){
        return "/dept/dept_add";
    }

    @PostMapping(value = "/dept_added")
    public String added(@Validated Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int queryResult = departmentService.insertSelective(department);
        if (queryResult == 1) {
            return "redirect:/dept/dept_list";
        }else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "服务器繁忙，请稍后重试");
            return "/dept/dept_add";
        }
    }

    @GetMapping(value = "/dept_update")
    public String update(Model model){
        Department objectId = new Department();
        model.addAttribute("objectId", objectId);
        List<Department> objects = departmentService.selectAllDept();
        model.addAttribute("objects", objects);
        return "/dept/dept_update";
    }

    @GetMapping(value = "/dept_update/{id}")
    public String updateById(@PathVariable Long id, Model model){
        if (id != null) {
            Department objectId = departmentService.selectById(id);
            model.addAttribute("objectId", objectId);
        }
        List<Department> objects = departmentService.selectAllDept();
        model.addAttribute("objects", objects);
        return "/dept/dept_update";
    }

    @PostMapping(value = "/dept_updated")
    public String updated(@Validated Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errorList = result.getAllErrors();
            throw new CustomException(errorList.toString());
        }
        int queryResult = departmentService.updateByIdSelective(department);
        if (queryResult == 1) {
            return "redirect:/dept/dept_list";
        }else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "服务器繁忙，请稍后重试");
            return "/dept/dept_update";
        }
    }
}
