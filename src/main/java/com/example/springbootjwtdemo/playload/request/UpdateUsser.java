package com.example.springbootjwtdemo.playload.request;

public class UpdateUsser {

    private String username;
    private String email;

    private  String roles;
    private String departments;
    private  String phone;
    private String address;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getRole() {
        return roles;
    }
    public void setRole(String role) {
        this.roles = role;
    }

    public String getDepartment() {return departments;}
    public void setDepartment(String department) {this.departments = department;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getPhone() {return phone;}

    public void setAddress(String address) {this.address = address;}

    public String getAddress() {return address;}
}
