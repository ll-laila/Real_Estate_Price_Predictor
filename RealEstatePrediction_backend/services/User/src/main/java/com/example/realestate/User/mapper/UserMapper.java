package com.example.realestate.User.mapper;

import com.example.realestate.User.entity.User;
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(UserRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .id(request.id())
                .username(request.username())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phone(request.phone())
                .password(request.password())
                .build();
    }

    public UserResponse fromUser(User user) {

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getPhone()
        );
    }
}
