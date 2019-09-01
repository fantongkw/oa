package com.ccc.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/app")
public class AppController {

    @GetMapping(value = "/email")
    public String email() {
        return "/app/email";
    }

    @GetMapping(value = "/calendar")
    public String calendar() {
        return "/app/calendar";
    }

    @GetMapping(value = "/chat")
    public String chat(Model model) {
        return "/app/chat";
    }

}
