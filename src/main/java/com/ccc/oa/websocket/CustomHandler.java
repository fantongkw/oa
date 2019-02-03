package com.ccc.oa.websocket;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.socket.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class CustomHandler implements WebSocketHandler {
    private static final Map<String, WebSocketSession> users = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("成功建立连接");
        URI uri = webSocketSession.getUri();
        if (uri != null) {
            String id = uri.toString().split("ID=")[1];
            System.out.println(id);
            if (id != null) {
                users.put(id, webSocketSession);
                webSocketSession.sendMessage(new TextMessage("成功建立socket连接"));
            }
        }
        System.out.println("当前在线人数：" + users.size());

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
