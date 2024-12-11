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
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .password(request.password())
                .build();
    }

    public UserResponse fromUser(User user) {

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}


//this is for keycloak just in security remove comments
//package com.example.realestate.User.mapper;
//
//
//import com.example.realestate.User.dto.UserDTO;
//import org.keycloak.representations.idm.UserRepresentation;
//
//public class UserMapper {
//
//    // Map Keycloak UserRepresentation to UserDTO
//    public static UserDTO toDTO(UserRepresentation userRepresentation) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(userRepresentation.getId());
//        userDTO.setUsername(userRepresentation.getUsername());
//        userDTO.setEmail(userRepresentation.getEmail());
//        userDTO.setFirstName(userRepresentation.getFirstName());
//        userDTO.setLastName(userRepresentation.getLastName());
//        userDTO.setEnabled(userRepresentation.isEnabled());
//
//        if (userRepresentation.getAttributes() != null) {
//            userDTO.setAttributes(userRepresentation.getAttributes());
//        }
//
//        return userDTO;
//    }
//
//    // Map UserDTO to Keycloak UserRepresentation
//    public static UserRepresentation toRepresentation(UserDTO userDTO) {
//        UserRepresentation userRepresentation = new UserRepresentation();
//        userRepresentation.setId(userDTO.getId());
//        userRepresentation.setUsername(userDTO.getUsername());
//        userRepresentation.setEmail(userDTO.getEmail());
//        userRepresentation.setFirstName(userDTO.getFirstName());
//        userRepresentation.setLastName(userDTO.getLastName());
//        userRepresentation.setEnabled(userDTO.isEnabled());
//
//        if (userDTO.getAttributes() != null) {
//            userRepresentation.setAttributes(userDTO.getAttributes());
//        }
//
//        return userRepresentation;
//    }
//}
