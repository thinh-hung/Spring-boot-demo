package com.example.springbootjwt5.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users"
//        , uniqueConstraints = {
//                @UniqueConstraint(columnNames = "username"),
//                @UniqueConstraint(columnNames = "email")}
        )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 20,message = "length Max=20")
    private String username;
    @NotBlank(message = "Email is mandatory")
    @Size(max = 50,message = "length email Max=50")
    @Email(message = "incorrect form Email")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(max = 120,message = "Lenght password Max=120")
    private String password;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "roleName", referencedColumnName = "roleName")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "role_user",
        joinColumns = @JoinColumn(name = "idUser"),
        inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Role roles ;

//    @OneToOne
//    @JoinColumn(name = "departments_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_department",
        joinColumns = @JoinColumn(name = "idUser"),
        inverseJoinColumns = @JoinColumn(name = "idDepartment"))
    private Department departments ;
    public User() {
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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
//    public Set<Role> getRoles() {
//        return roles;
//    }
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    public void setRoles(Role roles) {
        this.roles = roles;
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
}
