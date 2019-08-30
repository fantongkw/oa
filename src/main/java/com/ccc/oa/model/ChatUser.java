package com.ccc.oa.model;

import java.io.Serializable;
import java.util.Calendar;

public class ChatUser implements Serializable {
    private static final long serialVersionUID = 8067800027078364029L;
    private String username;
    private String avatar;
    private String lastMessage;
    private Long connectionTime;

    public ChatUser() {
    }

    public ChatUser(String username, String avatar, String lastMessage, Long connectionTime) {
        this.username = username;
        this.avatar = avatar;
        this.lastMessage = lastMessage;
        this.connectionTime = connectionTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getConnectionTime() {
        return this.connectionTime;
    }

    public void setConnectionTime(Long connectionTime) {
        this.connectionTime = connectionTime;
    }
}
