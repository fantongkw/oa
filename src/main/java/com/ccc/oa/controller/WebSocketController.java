package com.ccc.oa.controller;

import com.ccc.oa.model.Message;
import com.ccc.oa.service.UserService;
import com.ccc.oa.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;


@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    private final UserService userService;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template, UserService userService) {
        this.template = template;
        this.userService = userService;
    }


    @MessageMapping("welcome")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) {
        System.out.println("后台广播推送！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("chat")
    public void handleMessage(Principal principal, Message message) {
        System.out.println("后台一对一推送！");

        template.convertAndSendToUser(principal.getName(), "/queue/msg", principal.getName() + "-send:"
                + HtmlUtils.htmlEscape(message.getName()));
        /*if (principal.getName().equals("aaa")) {
            template.convertAndSendToUser("bbb", "/queue/msg", principal.getName() + "-send:"
                    + message.getName());
        } else {
            template.convertAndSendToUser("aaa", "/queue/msg", principal.getName() + "-send:"
                    + message.getName());
        }*/
    }
}