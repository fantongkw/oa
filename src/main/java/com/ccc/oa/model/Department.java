package com.ccc.oa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Department implements Serializable {
    private static final long serialVersionUID = 5223395119656568793L;
    private Long id;
    @NotEmpty(message = "部门名不能为空")
    @Size(min = 4, max = 20)
    private String name;
    private Date date;
    @Size(max = 100)
    private String description;
    @JsonIgnore
    private Long parentId;
    @JsonIgnore
    private List<Member> members;
    @JsonIgnore
    private Department parent;
    @JsonIgnore
    private List<Department> children;

    public Department() {
    }

    public Department(Department department) {
        this.id = department.id;
        this.name = department.name;
        this.date = department.date;
        this.description = department.description;
        this.parentId = department.parentId;
        this.members = department.members;
        this.parent = department.parent;
        this.children = department.children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}