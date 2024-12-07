//package com.example.realestate.User.service;
//
//
//import com.example.realestate.User.config.KeycloakConfig;
//import com.example.realestate.User.entity.User;
//import com.example.realestate.User.exception.UserNotFoundException;
//import com.example.realestate.User.mapper.UserMapper;
//import com.example.realestate.User.repository.UserRepository;
//import com.example.realestate.User.request.UserRequest;
//import com.example.realestate.User.response.UserResponse;
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//import jakarta.ws.rs.core.Response;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang.StringUtils;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.resource.UsersResource;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static java.lang.String.format;
//
//// @Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final UserMapper userMapper;
//    private final Keycloak keycloakClient;
//    private final KeycloakConfig keycloakConfig;
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Transactional
//    public String createUser(UserRequest userRequest) {
//        try {
//            UserRepresentation keycloakUser = new UserRepresentation();
//            keycloakUser.setUsername(userRequest.username());
//            keycloakUser.setEmail(userRequest.email());
//            keycloakUser.setFirstName(userRequest.firstName());
//            keycloakUser.setLastName(userRequest.lastName());
//            keycloakUser.setEnabled(true);
//
//            CredentialRepresentation credential = new CredentialRepresentation();
//            credential.setType(CredentialRepresentation.PASSWORD);
//            credential.setValue(userRequest.password());
//            credential.setTemporary(false);
//
//            keycloakUser.setCredentials(Collections.singletonList(credential));
//
//            // UsersResource response = keycloakConfig.keycloakClient.realm(realm).users().create(keycloakUser);
//            Response response = keycloakClient.realm(realm).users().create(keycloakUser);
//            if (response.getStatus() == 201) {
//                // Extract the user ID from the response location
//                String locationHeader = response.getHeaderString("Location");
//                String userId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
//
//                return userId;
//            } else {
//                throw new RuntimeException("Failed to create Keycloak user. Status: " + response.getStatus());
//            }
//
////            if (response.getStatus() == 201) {
////                String keycloakUserId = extractKeycloakUserId(response);
////
////                User user = userMapper.toUser(userRequest);
////                user.setKeycloakId(keycloakUserId);
////                user = userRepository.save(user);
////
////                return user.getKeycloakId();
////            } else {
////                // Log the full response for debugging
////                String errorDetails = response.readEntity(String.class);
////                throw new RuntimeException("Failed to create user in Keycloak. Status: " +
////                        response.getStatus() + ", Details: " + errorDetails);
////            }
//        } catch (Exception e) {
//            // Log the full exception for debugging
//
//            throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
//        }
//    }
//
//    private String extractKeycloakUserId(Response response) {
//        URI location = response.getLocation();
//        if (location != null) {
//            String path = location.getPath();
//            return path.substring(path.lastIndexOf('/') + 1);
//        }
//        throw new RuntimeException("Could not extract Keycloak user ID");
//    }
//    public void updateUser(@Valid UserRequest userRequest) {
//
//        var user = userRepository.findById(userRequest.id())
//                .orElseThrow(()-> new UserNotFoundException(
//                   format("Cannot update user :: NO user found with the provided id :: %s", userRequest.id())
//                ));
//
//        mergeUser(user, userRequest);
//        userRepository.save(user);
//    }
//
//    private void mergeUser(User user, @Valid UserRequest userRequest) {
//
//        if (StringUtils.isNotBlank(userRequest.firstName())) {
//            user.setFirstName(userRequest.firstName());
//        }
//        if (StringUtils.isNotBlank(userRequest.lastName())) {
//            user.setLastName(userRequest.lastName());
//        }
//        if (StringUtils.isNotBlank(userRequest.email())) {
//            user.setEmail(userRequest.email());
//        }
//    }
//
//    public List<UserResponse> findAllUsers() {
//        return userRepository.findAll()
//                .stream()
//                .map(userMapper :: fromUser)
//                .collect(Collectors.toList());
//    }
//
//    public Boolean existsById(String userId) {
//        return userRepository.findById(userId).isPresent();
//    }
//
//    public UserResponse findById(String userId) {
//        return userRepository.findById(userId)
//                .map(userMapper::fromUser)
//                .orElseThrow(()-> new UserNotFoundException(format("Cannot find user :: %s", userId)));
//    }
//
//    public void deleteUser(String userId) {
//        userRepository.deleteById(userId);
//    }
//}
