package com.example.realestate.User.controller;

<<<<<<< HEAD
import com.example.realestate.User.dto.UserRequest;
import com.example.realestate.User.dto.UserResponse;
import com.example.realestate.User.entity.User;
import com.example.realestate.User.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
=======
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import com.example.realestate.User.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
>>>>>>> 6eb59159e5dd8282b5e60b3541bb7d75950b4334

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    private UserService userService;

    @GetMapping("/testUser")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service User is working!");
    }
<<<<<<< HEAD
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
=======

    @PutMapping
    public ResponseEntity<Void> updateUser(
            @RequestBody @Valid UserRequest userRequest)
    {
        userService.updateUser(userRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll()
    {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/exists/{user-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("user-id") String userId)
    {
        return ResponseEntity.ok(userService.existsById(userId));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("user-id") String userId)
    {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("user-id") String userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.accepted().build();
>>>>>>> 6eb59159e5dd8282b5e60b3541bb7d75950b4334
    }
}


