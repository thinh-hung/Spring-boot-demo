package com.example.springbootjwtdemo.playload.response;

import java.io.Serializable;
import java.util.Date;

/*
This is class is required for creating a response containing the JWT to be returned to the user.
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    String jwttoken;
    String userName;

    Date expires;
    String role;
    String avatar;


    public JwtResponse(String jwttoken,Date time,String role,String userName,String avatar) {
        this.jwttoken = jwttoken;
        this.expires = time;
        this.role = role;
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public Date getDate() {
        return expires;
    }

    public String getRole() {
        return role;
    }

    public String getUserName() {return userName;}

    public String getAvatar() {return avatar;}
}
