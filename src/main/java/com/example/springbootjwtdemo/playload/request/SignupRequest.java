package com.example.springbootjwt5.playload.request;

import java.util.Set;

public class SignupRequest {
    private String username;
    private String email;
    private String password;
//    private Set<String> role;
    private  String role;
    private String department;

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

//    public Set<String> getRole() {
//        return this.role;
//    }
//
//    public void setRole(Set<String> role) {
//        this.role = role;
//    }


    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {return department;}
    public void setDepartment(String department) {this.department = department;}
}
