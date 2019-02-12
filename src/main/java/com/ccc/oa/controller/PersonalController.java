package com.ccc.oa.controller;

import com.ccc.oa.model.Department;
import com.ccc.oa.model.Member;
import com.ccc.oa.model.Role;
import com.ccc.oa.security.CurrentUser;
import com.ccc.oa.service.UserService;
import com.ccc.oa.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/personal")
@CrossOrigin
public class PersonalController {
    private final UserService userService;

    @Autowired
    public PersonalController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String account(@CurrentUser User user, Model model){
        System.out.println(user);
        Member account = userService.loadUserByUsername(user.getUsername());
        model.addAttribute("user", account);
        return "/personal/account";
    }

    @PostMapping("/update_account")
    public String updated(@Validated Member member) {
        userService.updateByIdSelective(member);
        return "redirect:/personal/account";
    }

    @GetMapping("/dept_details")
    public String deptDetails(@CurrentUser User user, Model model){
        Department department = userService.loadUserByUsername(user.getUsername()).getDepartment();
        model.addAttribute("department", department);
        return "/personal/dept_details";
    }



    @GetMapping("/role_details")
    public String roleDetails(@CurrentUser User user, Model model){
        Role role = userService.loadUserByUsername(user.getUsername()).getRole();
        model.addAttribute("role", role);
        return "/personal/role_details";
    }

    @GetMapping("/change_password")
    public String changePassword(){
        return "/personal/change_password";
    }

    @PostMapping("/changed_password")
    public String changedPassword(@CurrentUser User user, @RequestParam String password, HttpSession session, Model model){
        if (user == null) {
         return "redirect:/personal/change_password";
        }
        Member member = userService.loadUserByUsername(user.getUsername());
        int success = userService.changePassword(member, password);
        if (success == 1) {
            session.invalidate();
            return "redirect:/login";
        }
        model.addAttribute("error", true);
        model.addAttribute("errorMsg", "服务器繁忙，请稍后重试");
        return "/personal/change_password";
    }

    @GetMapping("/upload")
    public String upload() {
        return "/personal/upload";
    }

    @PostMapping("/uploaded")
    @ResponseBody
    public String uploaded(@RequestParam("file") MultipartFile file, @CurrentUser Member user) {
        return FileUtil.uploadFile(file);
    }

    @GetMapping("/chat")
    public String chat() {
        return "/chat";
    }
}
