package com.ccc.oa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private static final long serialVersionUID = -678506501735564646L;
    private Long id;
    @NotEmpty(message = "角色名不能为空")
    @Size(min = 4, max = 20)
    private String name;
    @Size(max = 100)
    private String description;
    @JsonIgnore
    private List<Member> members;
    @JsonIgnore
    private List<Permission> permissions;
    public Role() {
    }

    public Role(Role role) {
        this.id = role.id;
        this.name = role.name;
        this.description = role.description;
        this.members = role.members;
        this.permissions = role.permissions;
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}