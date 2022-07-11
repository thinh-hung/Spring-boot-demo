package com.example.springbootjwtdemo.controller;


import com.example.springbootjwtdemo.model.*;
import com.example.springbootjwtdemo.playload.request.LoginRequest;
import com.example.springbootjwtdemo.playload.request.SignupRequest;
import com.example.springbootjwtdemo.playload.response.JwtResponse;
import com.example.springbootjwtdemo.playload.response.MessageResponse;

import com.example.springbootjwtdemo.repository.DepartmentRepository;
import com.example.springbootjwtdemo.repository.RoleRepository;
import com.example.springbootjwtdemo.repository.UserRepository;
import com.example.springbootjwtdemo.security.jwt.JwtUtils;
import com.example.springbootjwtdemo.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwttoken = jwtUtils.generateToken(userDetails);
        Date jwtDate = jwtUtils.getExpirationDateFromToken(jwttoken);
        String userName = jwtUtils.getUserNameFromJWT(jwttoken) ;
        String avatar = userDetails.getAvatar();
        System.out.println("nhap: "+avatar);
        System.out.println("as: "+avatar);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String role1 = roles.get(0);
        return ResponseEntity.ok(new JwtResponse(jwttoken,jwtDate,role1,userName,avatar));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Name is already in use!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),signUpRequest.getAvatar(),signUpRequest.getPhone(),signUpRequest.getAddress());
//        Set<String> strRoles = signUpRequest.getRole();
        String strRoles = signUpRequest.getRole();
        Role roles = new Role() ;
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles = userRole;
        } else  {

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
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(new MessageResponse("You've been signed out!"));
//    }
}
