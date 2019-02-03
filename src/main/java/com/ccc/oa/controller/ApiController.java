package com.ccc.oa.controller;

import com.ccc.oa.model.Member;
import com.ccc.oa.service.UserService;
import com.ccc.oa.utils.ResultMsg;
import com.ccc.oa.utils.WeatherUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ApiController {

    private UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResultMsg getUserDetail(Principal principal){
        if (principal != null) {
            Member member = userService.loadUserByUsername(principal.getName());
            return new ResultMsg(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), member);
        }
        return new ResultMsg(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    @GetMapping("/username")
    public Boolean getUsername(String username){
        Member member = userService.loadUserByUsername(username);
        return member == null;
    }

    @GetMapping("/weather")
    public String getWeather() {
        String defaultCity = "410100";
        return WeatherUtil.request(defaultCity);
    }
}
