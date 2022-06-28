//package com.example.springbootjwtdemo.playload.response;
//
//import com.example.springbootjwtdemo.model.Role;
//import com.example.springbootjwtdemo.model.User;
//import com.example.springbootjwtdemo.repository.RoleRepository;
//import com.example.springbootjwtdemo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GetListUser {
//    @Autowired
//    Long id;
//    @Autowired
//    List<User> users;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
//
//
//
//    public GetListUser(Long id){
//        List<Role> role = roleRepository.findByIdRole(id);
//        Role role1 = role.get(0);
//        List<User> allUser = userRepository.findByRoles(role1);
//        List<User> userList = new ArrayList<User>();
//        for (User u:allUser){
//            if (u.getRoles().getId() == id){
//                userList.add(u);
//            }
//        }
//        this.users=userList;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//}
