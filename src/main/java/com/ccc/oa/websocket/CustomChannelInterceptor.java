package com.ccc.oa.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

public class CustomChannelInterceptor implements ChannelInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(CustomChannelInterceptor.class);

    @Override
    public Message<?> preSend(@NonNull Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null) {
            StompCommand command = accessor.getCommand();
            if (StompCommand.CONNECT.equals(command)) {
                String ttlString = accessor.getFirstNativeHeader("ttl");
                long ttl = 30000;
                if(ttlString != null) {
                    try {
                        ttl = Long.parseLong(ttlString);
                    }
                    catch(Exception ex) {
                        LOG.error("TTL header received but not in correct format {}",ttlString);
                    }
                }
                accessor.addNativeHeader("expires", Long.toString(System.currentTimeMillis() + ttl));
            }
        }
        return message;
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {

    }
}
