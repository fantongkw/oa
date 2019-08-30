package com.ccc.oa.service.impl;

import com.ccc.oa.model.ChatUser;
import com.ccc.oa.service.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service(value = "chatUserService")
public class ChatUserServiceImpl implements ChatUserService {
    private final String namespace = "websocket:chat:users:";

    private final RedisTemplate<String, ChatUser> redisTemplate;

    @Autowired
    public ChatUserServiceImpl(RedisTemplate<String, ChatUser> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(getAllUser());
        if (keys == null) return;
        redisTemplate.delete(keys);
    }

    @Override
    public void delete(String id) {
        redisTemplate.delete(getUserKey(id));
    }

    @Override
    public void add(String id, ChatUser chatUser) {
        redisTemplate.opsForValue().set(getUserKey(id), chatUser);
    }

    @Override
    public List<ChatUser> getAll() {
        List<ChatUser> res = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(getAllUser());
        if (keys != null) {
            keys.forEach(e -> {
                ChatUser chatUser = redisTemplate.opsForValue().get(e);
                res.add(chatUser);
            });
        }
        return res;
    }

    @Override
    public ChatUser get(String id) {
        return redisTemplate.opsForValue().get(getUserKey(id));
    }

    @Override
    public void update(String id, ChatUser chatUser) {
        redisTemplate.opsForValue().set(getUserKey(id), chatUser);
    }

    private String getAllUser() {
        return this.namespace + "*";
    }

    private String getUserKey(String id) {
        return this.namespace + id;
    }
}
