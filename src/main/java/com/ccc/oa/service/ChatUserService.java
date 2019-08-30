package com.ccc.oa.service;

import com.ccc.oa.model.ChatUser;

import java.util.List;

public interface ChatUserService {
    void deleteAll();

    void delete(String id);

    void add(String id, ChatUser chatUser);

    List<ChatUser> getAll();

    ChatUser get(String id);

    void update(String id, ChatUser chatUser);
}
