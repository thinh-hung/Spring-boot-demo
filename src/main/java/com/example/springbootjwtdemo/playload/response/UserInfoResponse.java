package com.example.springbootjwtdemo.playload.response;

import com.example.springbootjwtdemo.model.User;

public class UserInfoResponse {

    private Long id;
    private String username;
    private  String password;
    private String avatar;
    private String phone;
    private String address;
    private String email;
    private String roles;
    private  String departments;

    public UserInfoResponse(User user){
        this.id = user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.email=user.getEmail();
        this.roles = user.getRoles().getName().toString();
        this.departments = user.getDepartments().getName().toString();
        this.avatar = user.getAvatar();
        this.phone = user.getPhone();
        this.address =user.getAddress();
    }
    public UserInfoResponse(Long id, String username, String email,String password,String roles, String departments,String avatar) {
        this.id=id;
        this.username=username;
        this.email=email;
        this.password=password;
        this.roles = roles;
        this.departments = departments;
        this.avatar = avatar;
    }
    public UserInfoResponse(Long id, String username, String email,String password,String address, String phone) {
        this.id=id;
        this.username=username;
        this.email=email;
        this.password=password;
        this.phone = phone;
        this.address =address;
    }


    public String getAvatar() {return avatar;}

    public void setAvatar(String avatar) {this.avatar = avatar;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {this.roles = roles;}

    public String getDepartments() {return departments;}

    public void setDepartments(String departments) {this.departments = departments;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getPhone() {return phone;}
}
