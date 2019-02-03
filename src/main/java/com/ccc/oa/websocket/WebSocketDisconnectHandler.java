package com.ccc.oa.websocket;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class WebSocketDisconnectHandler<S> implements ApplicationListener {
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

    }
}
