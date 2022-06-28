package com.example.springbootjwt5.controller;
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
    @PreAuthorize("hasRole('STAFF') or hasRole('LEADER') or hasRole('FUNDS')")
    public String userAccess() {
        return "Staff.";
    }
    @GetMapping("/leader")
    @PreAuthorize("hasRole('LEADER')")
    public String moderatorAccess() {
        return "Leader Board.";
    }
    @GetMapping("/funds")
    @PreAuthorize("hasRole('FUNDS')")
    public String adminAccess() {
        return "Funds Board.";
    }
}