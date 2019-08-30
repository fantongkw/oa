package com.ccc.oa.service;


import com.ccc.oa.model.ChatMessage;

import java.util.List;
import java.util.Map;

public interface ChatMessageService {
    void deleteAll();

    void deleteUserMessage(String from);

    void deleteFromUserToUser(String from, ChatMessage chatMessage);

    void add(String from, ChatMessage chatMessage);

    Map<String, List<ChatMessage>> getAll();

    List<ChatMessage> getUserMessage(String from);

    List<ChatMessage> getFromUserToUser(String from, String to);

}
