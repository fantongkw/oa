package com.ccc.oa.websocket;

import com.ccc.oa.service.ChatUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

public class WebSocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {
    private ChatUserService chatUserService;
    private SimpMessagingTemplate template;

    public WebSocketDisconnectHandler(ChatUserService chatUserService, SimpMessagingTemplate template) {
        this.chatUserService = chatUserService;
        this.template = template;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        Principal user = SimpMessageHeaderAccessor.getUser(headers);
        if (user == null) {
            return;
        }
        template.convertAndSend("/topic/status/disconnect", user.getName());
        chatUserService.delete(user.getName());
    }
}
