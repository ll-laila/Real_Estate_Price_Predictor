package com.example.realestate.User.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/testUser")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service User is working!");
    }
}
