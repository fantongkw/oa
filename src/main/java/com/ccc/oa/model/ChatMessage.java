package com.ccc.oa.model;

import java.io.Serializable;
import java.util.Calendar;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = -1264304440511204192L;
    private String to;
    private String from;
    private String message;
    private Long created;

    public ChatMessage() {
    }

    public ChatMessage(String to, String from, String message, Long created) {
        this.to = to;
        this.from = from;
        this.message = message;
        this.created = created;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCreated() {
        return this.created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", message='" + message + '\'' +
                ", created=" + created +
                '}';
    }
}
