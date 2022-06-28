package com.example.springbootjwtdemo.playload.request;

public class SignupRequest {
    private String username;
    private String email;
    private String password;
//    private Set<String> role;
    private  String role;
    private String department;
    private String avatar;
    private  String phone;
    private String address;




    public String getAvatar() {return avatar;}
    public void setAvatar(String avatar) {this.avatar = avatar;}

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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {return department;}
    public void setDepartment(String department) {this.department = department;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getPhone() {return phone;}

    public void setAddress(String address) {this.address = address;}

    public String getAddress() {return address;}
}
