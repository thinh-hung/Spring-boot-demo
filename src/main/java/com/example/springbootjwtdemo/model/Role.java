package com.example.springbootjwt5.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "roleName",length = 50)
    private ERole name;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "role_user",
//            joinColumns = @JoinColumn(name = "idUser"),
//            inverseJoinColumns = @JoinColumn(name = "idRole"))
//    private User users;

    public Role() {
    }
    public Role(ERole name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ERole getName() {
        return name;
    }
    public void setName(ERole name) {
        this.name = name;
    }
//    public void setUsers(User users) {
//        this.users = users;
//    }
//
//    public User getUsers() {
//        return users;
//    }
}