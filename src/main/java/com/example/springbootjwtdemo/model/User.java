package com.example.springbootjwtdemo.model;

import org.hibernate.annotations.Filter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users"
        , uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")}
        )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 20,message = "length Max=20")
    private String username;

    @NotBlank(message = "Avatar is mandatory")
    private String avatar;
    @NotBlank(message = "Phone is mandatory")
    private String phone;
    @NotBlank(message = "Avatar is mandatory")
    private String address;
    @NotBlank(message = "Email is mandatory")
    @Size(max = 50,message = "length email Max=50")
    @Email(message = "incorrect form Email")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(max = 120,message = "Lenght password Max=120")
    private String password;
    @lombok.Setter
    @OneToOne()
    @JoinColumn(name = "roleId")
    private Role roles ;

    @OneToOne
    @JoinColumn(name = "departmentsId")
    private Department departments ;

    public User() {
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String email, String password,String avatar,String phone,String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.phone = phone;
        this.address= address;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail(String email) {
        return this.email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword(String password) {
        return this.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoles() {
        return roles;
    }

    public void setDepartments(Department departments) {
        this.departments = departments;
    }

    public Department getDepartments() {
        return departments;
    }

    public String getAvatar() {return avatar;}

    public void setAvatar(String avatar) {this.avatar = avatar;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}
}
