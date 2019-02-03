package com.ccc.oa.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.security.Principal;

public class WebSocketConnectHandler<S> implements ApplicationListener<SessionConnectEvent> {
    private SimpMessageSendingOperations messagingTemplate;

    public WebSocketConnectHandler(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        Principal principal = SimpMessageHeaderAccessor.getUser(headers);
        if (principal == null) {
            return;
        }
        String id = SimpMessageHeaderAccessor.getSessionId(headers);
    }
}
