package com.example.springbootjwtdemo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nameDeparments",length = 50)
    private EDevelopment name;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_department",
//            joinColumns = @JoinColumn(name = "idDepartment"),
//            inverseJoinColumns = @JoinColumn(name = "nameUser", referencedColumnName = "username"))
//    @OneToMany(mappedBy = "departments")
//    private List<User> users ;


    public Department() {
    }
    public Department(EDevelopment name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public EDevelopment getName() {
        return name;
    }
    public void setName(EDevelopment name) {
        this.name = name;
    }

//    public User getUsers() {
//        return users;
//    }
//
//    public void setUsers(User users) {
//        this.users = users;
//    }
}
