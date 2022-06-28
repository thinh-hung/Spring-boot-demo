package com.example.springbootjwt5.controller;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.example.springbootjwt5.model.*;
import com.example.springbootjwt5.playload.request.LoginRequest;
import com.example.springbootjwt5.playload.request.SignupRequest;
import com.example.springbootjwt5.playload.response.MessageResponse;
import com.example.springbootjwt5.playload.response.UserInfoResponse;
import com.example.springbootjwt5.repository.DepartmentRepository;
import com.example.springbootjwt5.repository.RoleRepository;
import com.example.springbootjwt5.repository.UserRepository;
import com.example.springbootjwt5.security.jwt.JwtUtils;
import com.example.springbootjwt5.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
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
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String role1 = roles.get(0);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body("Your role: "+new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        role1).getTokenType());
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
//        Set<String> strRoles = signUpRequest.getRole();
        String strRoles = signUpRequest.getRole();
        Role roles = new Role() ;
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_STAFF)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles = userRole;
        } else {

            switch (strRoles) {
                case "leader":
                    roles = roleRepository.findByName(ERole.ROLE_LEADER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                    break;
                case "funds":
                    roles= roleRepository.findByName(ERole.ROLE_FUNDS)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                    break;
                default:
                    roles = roleRepository.findByName(ERole.ROLE_STAFF)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            }
        }
        user.setRoles(roles);

        String strDepartment = signUpRequest.getDepartment();
        Department departments = new Department();

        if (strDepartment == null) {
            departments = departmentRepository.findByName(EDevelopment.RESOUCES_DEPARTMENT)
                    .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
        } else {

            switch (strDepartment) {
                case "accounting":
                    departments = departmentRepository.findByName(EDevelopment.ACCOUNTING_DEPARTMENT)
                            .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
                    break;
                case "marketing":
                    departments = departmentRepository.findByName(EDevelopment.MARKETING_DEPARTMENT)
                            .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
                    break;
                default:
                    departments = departmentRepository.findByName(EDevelopment.RESOUCES_DEPARTMENT)
                            .orElseThrow(() -> new RuntimeException("Error: Departments is not found."));
            }
        }
        user.setDepartments(departments);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
