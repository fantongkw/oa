package com.ccc.oa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@JsonIgnoreProperties(value = { "handler" })
public class Member implements Serializable {
    private static final long serialVersionUID = 6834568878933467652L;
    private Long id;
    @JsonIgnore
    private Long departmentId;
    @JsonIgnore
    private Department department;
    @JsonIgnore
    private Long roleId;
    private Role role;
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 4)
    private String username;
    @Size(min = 8, max = 20, message = "密码长度规定在8到20之间")
    @JsonIgnore
    private String password;
    private String name;
    private String gender;
    private String phone;
    //@Email(regexp = "^[a-z0-9]+@([a-z0-9]+\\.)+[a-z]{2,6}$", message = "邮箱格式不正确")
    private String email;
    private String avatar;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    @Size(max = 100, message = "描述长度最大100")
    private String description;

    public Member() {}

    public Member(Member member) {
        this.id = member.id;
        this.departmentId = member.departmentId;
        this.department = member.department;
        this.roleId = member.roleId;
        this.role = member.role;
        this.username = member.username;
        this.password = member.password;
        this.name = member.name;
        this.gender = member.gender;
        this.phone = member.phone;
        this.email = member.email;
        this.avatar = member.avatar;
        this.date = member.date;
        this.description = member.description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Member{" +
                " username='" + username + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}