package com.example.realestate.User.dto;

import com.example.realestate.User.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public User toCustomer(UserRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .id(request.id())
                .firstName(request.firstname())
                .lastName(request.lastname())
                .email(request.email())
                .build();
    }

    public UserResponse fromCustomer(User customer) {
        if (customer == null) {
            return null;
        }
        return new UserResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }
}
