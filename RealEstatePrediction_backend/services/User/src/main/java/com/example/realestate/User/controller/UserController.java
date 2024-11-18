package com.example.realestate.User.controller;

import com.example.realestate.User.dto.UserRequest;
import com.example.realestate.User.dto.UserResponse;
import com.example.realestate.User.entity.User;
import com.example.realestate.User.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/testUser")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service User is working!");
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(this.service.findById(customerId));
    }
    @PostMapping("/add")
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid UserRequest request
    ) {
        return ResponseEntity.ok(this.service.createCustomer(request));
    }
}
