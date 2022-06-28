//package com.example.springbootjwtdemo.controller;
//
//import com.example.springbootjwtdemo.exception.ResourceNotFoundException;
//import com.example.springbootjwtdemo.model.Role;
//import com.example.springbootjwtdemo.model.User;
////import com.example.springbootjwtdemo.playload.response.GetListUser;
//import com.example.springbootjwtdemo.playload.response.UserInfoResponse;
//import com.example.springbootjwtdemo.repository.RoleRepository;
//import com.example.springbootjwtdemo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/role")
//public class RoleController {
//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//    UserRepository userRepository;
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getRoleUserId(@PathVariable Long id) throws ResourceNotFoundException {
//        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + id));
//        List<User> allUsers = userRepository.findByRoles(role);
//        List<UserInfoResponse> userInfoResponseList = new ArrayList<UserInfoResponse>();
//        for (int i=0;i<allUsers.size();i++){
//            System.out.println("qua if");
//            User user = allUsers.get(i);
//            userInfoResponseList.add(new UserInfoResponse(user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),user.getRoles().getName().toString(),user.getDepartments().getName().toString()));
//        }
//        return new ResponseEntity<>(userInfoResponseList , HttpStatus.OK);
//    }
//
//}
