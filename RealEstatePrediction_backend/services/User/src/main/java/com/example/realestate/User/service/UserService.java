package com.example.realestate.User.service;


<<<<<<< HEAD

import com.example.realestate.User.dto.CustomerMapper;
import com.example.realestate.User.dto.UserRequest;
import com.example.realestate.User.dto.UserResponse;
import com.example.realestate.User.entity.User;
import com.example.realestate.User.repository.UserRepository;
=======
import com.example.realestate.User.entity.User;
import com.example.realestate.User.exception.UserNotFoundException;
import com.example.realestate.User.mapper.UserMapper;
import com.example.realestate.User.repository.UserRepository;
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import jakarta.validation.Valid;
>>>>>>> 6eb59159e5dd8282b5e60b3541bb7d75950b4334
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
>>>>>>> 6eb59159e5dd8282b5e60b3541bb7d75950b4334

@Service
@RequiredArgsConstructor
public class UserService {
<<<<<<< HEAD
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
=======

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void updateUser(@Valid UserRequest userRequest) {

        var user = userRepository.findById(userRequest.id())
                .orElseThrow(()-> new UserNotFoundException(
                   format("Cannot update user :: NO user found with the provided id :: %s", userRequest.id())
                ));

        mergeUser(user, userRequest);
        userRepository.save(user);
    }

    private void mergeUser(User user, @Valid UserRequest userRequest) {

        if (StringUtils.isNotBlank(userRequest.firstName())) {
            user.setFirstName(userRequest.firstName());
        }
        if (StringUtils.isNotBlank(userRequest.lastName())) {
            user.setLastName(userRequest.lastName());
        }
        if (StringUtils.isNotBlank(userRequest.email())) {
            user.setEmail(userRequest.email());
        }
    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper :: fromUser)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String userId) {
        return userRepository.findById(userId).isPresent();
    }

    public UserResponse findById(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::fromUser)
                .orElseThrow(()-> new UserNotFoundException(format("Cannot find user :: %s", userId)));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
>>>>>>> 6eb59159e5dd8282b5e60b3541bb7d75950b4334
    }
}
