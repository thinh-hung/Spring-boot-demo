package com.example.springbootjwt5.playload.request;

public class LoginRequest  {
    private String username;
    private  String password;

    public  LoginRequest(){
        super();
    }
    public LoginRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
