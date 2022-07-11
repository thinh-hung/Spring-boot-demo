package com.example.springbootjwtdemo.controller;

import com.example.springbootjwtdemo.exception.ResourceNotFoundException;
import com.example.springbootjwtdemo.model.*;
import com.example.springbootjwtdemo.playload.request.PutRequest;
import com.example.springbootjwtdemo.playload.request.UpdateUsser;
import com.example.springbootjwtdemo.playload.response.MessageResponse;
import com.example.springbootjwtdemo.playload.response.UserInfoResponse;
import com.example.springbootjwtdemo.playload.response.UserInfoResponseAM;
import com.example.springbootjwtdemo.repository.DepartmentRepository;
import com.example.springbootjwtdemo.repository.RoleRepository;
import com.example.springbootjwtdemo.repository.UserRepository;
import com.example.springbootjwtdemo.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/all")
    public ResponseEntity<?> getUserId(){
        List<User> users = userRepository.findAll();
        List<UserInfoResponse> userInfoResponses = new ArrayList<UserInfoResponse>();
        for (int i=0 ;i<users.size();i++){
            UserInfoResponse userInfoResponse = new UserInfoResponse(users.get(i));
            userInfoResponses.add(userInfoResponse);
        }
        return ResponseEntity.ok(userInfoResponses);
    }

    @GetMapping("/{username}")
    @PreAuthorize("isAuthenticated() ")
    public ResponseEntity<?> getUser(@PathVariable String username) throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found for this name :: " + username)));
        UserInfoResponseAM userInfoResponseAM = new UserInfoResponseAM(user.get());
        return ResponseEntity.ok(userInfoResponseAM);
    }
    @GetMapping("/individual/{username}")
    @PreAuthorize("isAuthenticated() ")
    public ResponseEntity<?> getPutUser(@PathVariable String username) throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found for this name :: " + username)));
        UserInfoResponse userInfoResponse = new UserInfoResponse(user.get());
        return ResponseEntity.ok(userInfoResponse);
    }
    @PutMapping ("/{username}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public ResponseEntity<?> putUserName(@PathVariable String username, @RequestBody UpdateUsser signUpRequest) throws ResourceNotFoundException {
        Optional<User> userdata = Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found for this name :: " + username)));
        User user = userdata.get();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPhone(signUpRequest.getPhone());
        user.setAddress(signUpRequest.getAddress());
        String strRoles = signUpRequest.getRole();
        Role roles = new Role() ;
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles = userRole;
        } else {

            switch (strRoles) {
                case "admin":
                    roles = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                    break;
                case "moderator":
                    roles= roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                    break;
                default:
                    roles = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            }
        }
        user.setRoles(roles);

        String strDepartment = signUpRequest.getDepartment();
        Department departments = new Department();

        if (strDepartment == null) {
            departments = departmentRepository.findByName(EDevelopment.DEPARTMENT_RESOURCES)
                    .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
        } else {

            switch (strDepartment) {
                case "accounting":
                    departments = departmentRepository.findByName(EDevelopment.DEPARTMENT_ACCOUNTING)
                            .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
                    break;
                case "marketing":
                    departments = departmentRepository.findByName(EDevelopment.DEPARTMENT_MARKETING)
                            .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
                    break;
                default:
                    departments = departmentRepository.findByName(EDevelopment.DEPARTMENT_RESOURCES)
                            .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
            }
        }
        user.setDepartments(departments);
        userRepository.save(user);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping ("/individual/{username}")
    @PreAuthorize("isAuthenticated() ")
    public ResponseEntity<?> putUser(@PathVariable String username, @RequestBody PutRequest putRequest) throws ResourceNotFoundException {
        Optional<User> userdata = Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found for this name :: " + username)));
        User user = userdata.get();
        System.out.println("pass: "+putRequest.getPassword());
        user.setPassword(encoder.encode(putRequest.getPassword()));
        user.setEmail(putRequest.getEmail());
        user.setPhone(putRequest.getPhone());
        user.setAddress(putRequest.getAddress());
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUserId(@PathVariable String username) throws ResourceNotFoundException  {
        try {
            Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this name :: " + username)));

                userRepository.delete(user.get());

            return ResponseEntity.ok(new MessageResponse("Delete successful"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
