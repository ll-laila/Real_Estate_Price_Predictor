package com.example.realestate.User.service;



import com.example.realestate.User.dto.CustomerMapper;
import com.example.realestate.User.dto.UserRequest;
import com.example.realestate.User.dto.UserResponse;
import com.example.realestate.User.entity.User;
import com.example.realestate.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final CustomerMapper mapper;
    public UserResponse findById(String id) {
        return this.repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new RuntimeException(String.format("No customer found with the provided ID: %s", id)));
    }
    public String createCustomer(UserRequest request) {
        var customer = this.repository.save(mapper.toCustomer(request));
        return customer.getId();
    }
}
