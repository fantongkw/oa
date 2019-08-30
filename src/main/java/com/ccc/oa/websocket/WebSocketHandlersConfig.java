package com.ccc.oa.websocket;

import com.ccc.oa.service.ChatUserService;
import com.ccc.oa.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.session.Session;

@Configuration
public class WebSocketHandlersConfig {

    @Bean
    public WebSocketConnectHandler webSocketConnectHandler(UserService userService, ChatUserService chatUserService, SimpMessagingTemplate template) {
        return new WebSocketConnectHandler(userService, chatUserService, template);
    }

    @Bean
    public WebSocketDisconnectHandler webSocketDisconnectHandler(ChatUserService chatUserService, SimpMessagingTemplate template) {
        return new WebSocketDisconnectHandler(chatUserService, template);
    }

}
