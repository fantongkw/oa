package com.ccc.oa.model;

public class ActiveWebSocketUser {
    private Long id;
    private String username;
    private Calendar connectionTime;

    public ActiveWebSocketUser() {
    }

    public ActiveWebSocketUser(Long id, String username, Calendar connectionTime) {
        super();
        this.id = id;
        this.username = username;
        this.connectionTime = connectionTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Calendar getConnectionTime() {
        return this.connectionTime;
    }

    public void setConnectionTime(Calendar connectionTime) {
        this.connectionTime = connectionTime;
    }
}
