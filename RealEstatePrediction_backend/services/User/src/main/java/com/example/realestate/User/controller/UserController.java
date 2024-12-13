package com.example.realestate.User.controller;


import com.example.realestate.User.config.UserAuthenticationProvider;
import com.example.realestate.User.dtos.CredentialsDto;
import com.example.realestate.User.dtos.SignUpDto;
import com.example.realestate.User.dtos.UserDto;
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import com.example.realestate.User.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getUsername()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(createdUser);
    }



    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

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

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("userId") String userId)
    {
        return ResponseEntity.ok(userService.findById(userId));
    }



    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("user-id") String userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }
}


