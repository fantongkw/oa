package com.ccc.oa.controller;

import com.ccc.oa.model.Member;
import com.ccc.oa.service.UserService;
import com.ccc.oa.websocket.WebSocketHandlersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/app")
public class AppController {

    private final UserService userService;

    @Autowired
    public AppController(UserService userService) {
        this.userService = userService;
    }

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
