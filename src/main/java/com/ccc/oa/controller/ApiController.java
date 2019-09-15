package com.ccc.oa.controller;

import com.ccc.oa.model.Member;
import com.ccc.oa.security.CurrentUser;
import com.ccc.oa.service.DepartmentService;
import com.ccc.oa.service.RoleService;
import com.ccc.oa.service.UserService;
import com.ccc.oa.utils.RedisSessionUtil;
import com.ccc.oa.utils.ResultMessage;
import com.ccc.oa.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final DepartmentService departmentService;
    private final UserService userService;
    private final RoleService roleService;
    private final RedisSessionUtil sessionUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApiController(DepartmentService departmentService, UserService userService, RoleService roleService,
                         RedisSessionUtil sessionUtil, PasswordEncoder passwordEncoder) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.roleService = roleService;
        this.sessionUtil = sessionUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

    @GetMapping("/user")
    public ResultMessage<Map<String, String>> getUserDetail(@CurrentUser User user) {
        Map<String, String> result = new HashMap<>();
        if (user != null) {
            Member member = userService.loadUserByUsername(user.getUsername());
            result.put("username", member.getUsername());
            result.put("role", member.getRole().getName());
            result.put("avatar", member.getAvatar());
            return new ResultMessage<>(HttpStatus.OK, result);
        }
        return new ResultMessage<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/data")
    public ResultMessage<Map<String, Integer>> getData() {
        Map<String, Integer> map = new HashMap<>();
        map.put("userOnLineCount", sessionUtil.getAllSession());
        map.put("deptCount", departmentService.selectAllDepartment().size());
        map.put("userCount", userService.selectAllUser().size());
        map.put("roleCount", roleService.selectAllRole().size());
        return new ResultMessage<>(HttpStatus.OK, map);
    }

    @GetMapping("/username")
    public Boolean getUserExists(String username) {
        Member member = userService.loadUserByUsername(username);
        return member == null;
    }

    @GetMapping("/password")
    public Boolean getPassword(@CurrentUser User user, String password) {
        boolean flag = false;
        if (user != null) {
            Member member = userService.loadUserByUsername(user.getUsername());
            flag = passwordEncoder.matches(password, member.getPassword());
        }
        return flag;
    }

    @GetMapping("/weather")
    public String getWeather() {
        String defaultCity = "410100";
        return WeatherUtil.request(defaultCity);
    }

}
