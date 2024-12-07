package com.example.realestate.User.mapper;


import com.example.realestate.User.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

public class UserMapper {

    // Map Keycloak UserRepresentation to UserDTO
    public static UserDTO toDTO(UserRepresentation userRepresentation) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userRepresentation.getId());
        userDTO.setUsername(userRepresentation.getUsername());
        userDTO.setEmail(userRepresentation.getEmail());
        userDTO.setFirstName(userRepresentation.getFirstName());
        userDTO.setLastName(userRepresentation.getLastName());
        userDTO.setEnabled(userRepresentation.isEnabled());

        if (userRepresentation.getAttributes() != null) {
            userDTO.setAttributes(userRepresentation.getAttributes());
        }

        return userDTO;
    }

    // Map UserDTO to Keycloak UserRepresentation
    public static UserRepresentation toRepresentation(UserDTO userDTO) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(userDTO.getId());
        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setEnabled(userDTO.isEnabled());

        if (userDTO.getAttributes() != null) {
            userRepresentation.setAttributes(userDTO.getAttributes());
        }

        return userRepresentation;
    }
}
