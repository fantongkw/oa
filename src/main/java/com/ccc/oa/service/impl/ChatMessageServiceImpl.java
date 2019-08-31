package com.ccc.oa.service.impl;

import com.ccc.oa.model.ChatMessage;
import com.ccc.oa.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service(value = "chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {

    private final String namespace = "websocket:chat:messages:";

    private final RedisTemplate<String, ChatMessage> redisTemplate;

    @Autowired
    public ChatMessageServiceImpl(RedisTemplate<String, ChatMessage> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(getAllMessage());
        if (keys == null) return;
        redisTemplate.delete(keys);
    }

    @Override
    public void deleteUserMessage(String id) {
        redisTemplate.delete(getMessageKey(id));
    }

    @Override
    public void deleteFromUserToUser(String from, ChatMessage chatMessage) {
        redisTemplate.opsForList().remove(getMessageKey(from), 1, chatMessage);
    }

    @Override
    public void add(String from, ChatMessage chatMessage) {
        redisTemplate.opsForList().rightPush(getMessageKey(from), chatMessage);
        redisTemplate.expire(getMessageKey(from), 30, TimeUnit.DAYS);
    }

    @Override
    public Map<String, List<ChatMessage>> getAll() {
        Map<String, List<ChatMessage>> res = new HashMap<>();
        Set<String> keys = redisTemplate.keys(getAllMessage());
        if (keys != null) {
            keys.forEach(e -> res.put(e, getUserMessage(e)));
        }
        return res;
    }

    @Override
    public List<ChatMessage> getUserMessage(String from) {
        return new ArrayList<>(Objects.requireNonNull(redisTemplate.opsForList().range(getMessageKey(from), 0, -1)));
    }

    @Override
    public List<ChatMessage> getFromUserToUser(String from, String to) {
        List<ChatMessage> res = new ArrayList<>();
        Objects.requireNonNull(redisTemplate.opsForList().range(getMessageKey(from), 0, -1)).forEach(e -> {
            if (e.getTo().equals(to)) {
                res.add(e);
            }
        });
        return res;
    }

    private String getAllMessage() {
        return this.namespace + "*";
    }

    private String getMessageKey(String id) {
        return this.namespace + id;
    }
}
