package com.ccc.oa.websocket;

import com.ccc.oa.model.ChatUser;
import com.ccc.oa.model.Member;
import com.ccc.oa.service.ChatUserService;
import com.ccc.oa.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.security.Principal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class WebSocketConnectHandler implements ApplicationListener<SessionConnectEvent> {
    private UserService userService;
    private ChatUserService chatUserService;
    private SimpMessagingTemplate template;

    public WebSocketConnectHandler(UserService userService, ChatUserService chatUserService, SimpMessagingTemplate template) {
        this.userService = userService;
        this.chatUserService = chatUserService;
        this.template = template;
    }

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        Principal user = SimpMessageHeaderAccessor.getUser(headers);
        if (user == null) {
            return;
        }
        Member member = userService.loadUserByUsername(user.getName());
        ChatUser chatUser = new ChatUser(member.getUsername(), member.getAvatar(), null, new Date().getTime());
        template.convertAndSend("/topic/status/connect", chatUser);
        chatUserService.add(user.getName(), chatUser);

    }
}
