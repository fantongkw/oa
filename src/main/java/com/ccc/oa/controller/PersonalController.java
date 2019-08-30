package com.ccc.oa.controller;

import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Role;
import com.ccc.oa.security.CurrentUser;
import com.ccc.oa.service.DepartmentService;
import com.ccc.oa.service.RoleService;
import com.ccc.oa.service.UserService;
import com.ccc.oa.utils.FileUtil;
import com.ccc.oa.utils.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/personal")
public class PersonalController {
    private final UserService userService;
    private final DepartmentService departmentService;
    private final RoleService roleService;

    @Autowired
    public PersonalController(UserService userService, DepartmentService departmentService, RoleService roleService) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.roleService = roleService;
    }

    @GetMapping("/account")
    public String account(@CurrentUser User user, Model model){
        Member account = userService.loadUserByUsername(user.getUsername());
        List<Department> departments = departmentService.selectAllDepartment();
        model.addAttribute("user", account);
        model.addAttribute("departments", departments);
        return "/personal/account";
    }

    @PostMapping("/update_account")
    public String updated(@Validated Member member) {
        userService.updateById(member);
        return "redirect:/personal/account";
    }

    @GetMapping("/dept_details")
    public String deptDetails(@CurrentUser User user, Model model){
        Long departmentId = userService.loadUserByUsername(user.getUsername()).getDepartmentId();
        Department department = departmentService.selectById(departmentId);
        model.addAttribute("department", department);
        return "/personal/dept_details";
    }

    @GetMapping("/role_details")
    public String roleDetails(@CurrentUser User user, Model model){
        Long roleId = userService.loadUserByUsername(user.getUsername()).getRoleId();
        Role role = roleService.selectById(roleId);
        model.addAttribute("role", role);
        return "/personal/role_details";
    }

    @GetMapping("/change_password")
    public String changePassword(){
        return "/personal/change_password";
    }

    @PostMapping("/changed_password")
    public String changedPassword(@CurrentUser User user, @RequestParam(value="new_password") String password, HttpSession session, Model model){
        if (user == null) {
         return "redirect:/personal/change_password";
        }
        Member member = userService.loadUserByUsername(user.getUsername());
        int success = userService.changePassword(member, password);
        if (success == 1) {
            session.invalidate();
            return "redirect:/login";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "修改密码失败");
            return "/personal/change_password";
        }

    }

    @GetMapping("/upload")
    public String upload() {
        return "/personal/upload";
    }

    @PostMapping("/uploaded")
    @ResponseBody
    public ResultMessage<String> uploaded(@NonNull String picture, @CurrentUser User user) {
        Member member = userService.loadUserByUsername(user.getUsername());
        String fileName = FileUtil.getUUIDFileName(picture);
        String result = FileUtil.uploadAvatar(picture, fileName);
        member.setAvatar(FileUtil.BASE_PATH + fileName);
        userService.updateById(member);
        return new ResultMessage<>(HttpStatus.OK, result);
    }
}
