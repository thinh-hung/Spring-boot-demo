package com.example.springbootjwtdemo.controller;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }


    @GetMapping("/staff")
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public String staffAccess() {
        return "Staff.";
    }
    @GetMapping("/leader")
    @PreAuthorize("hasAuthority('ROLE_LEADER')")
    public String leaderAccess() {
        return "Leader Board.";
    }
    @GetMapping("/funds")
    @PreAuthorize("hasAuthority('ROLE_FUNDS')")
    public String fundsAccess() {
        return "Funds Board.";
    }
}