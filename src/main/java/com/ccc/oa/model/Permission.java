package com.ccc.oa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Permission implements Serializable {
    private static final long serialVersionUID = 893073849086211911L;
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    private String url;
    @JsonIgnore
    private String method;
    @JsonIgnore
    private List<Role> roles;

    public Permission() {
    }

    public Permission(Permission permission) {
        this.id = permission.id;
        this.name = permission.name;
        this.description = permission.description;
        this.url = permission.url;
        this.method = permission.method;
        this.roles = permission.roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}