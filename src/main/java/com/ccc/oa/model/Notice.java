package com.ccc.oa.model;

import java.io.Serializable;
import java.util.Calendar;

public class Notice implements Serializable {
    private static final long serialVersionUID = 4942784013793429744L;
    private String id;
    private String type;
    private String username;
    private String title;
    private Long created;

    public Notice() {

    }

    public Notice(String id, String type, String username, String title, Long created) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.title = title;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", created=" + created +
                '}';
    }
}
