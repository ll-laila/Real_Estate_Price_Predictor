package com.example.realestate.User.service;
import jakarta.ws.rs.NotFoundException;
import org.apache.commons.lang.StringUtils;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.admin.client.resource.RealmResource;

import com.example.realestate.User.config.KeycloakConfigProperties;

import org.apache.commons.validator.routines.EmailValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakUserService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakUserService.class);

    @Autowired
    private KeycloakConfigProperties keycloakConfig;

    public String createUser(String username, String email, String firstName,
                           String lastName, String password) {
        // Input validation
        validateUserInput(username, email, password);

        Keycloak keycloak = null;
        try {
            keycloak = createKeycloakInstance();

            // Check if user already exists
            if (isUserExists(keycloak, username, email)) {
                // logger.info("User already exists with username or email: {}", username);
                throw new UserAlreadyExistsException("User already exists with username or email");


            }

            UserRepresentation user = createUserRepresentation(
                    username, email, firstName, lastName, password
            );

            // Create user in Keycloak
            Response response = keycloak.realm(keycloakConfig.getRealm())
                    .users()
                    .create(user);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed to create user. Response status: " + response.getStatus());
            }
            // Extract user ID from the response location
            String userId = extractUserIdFromResponse(response);

            logger.info("User created successfully: {}", username);
            return userId;
        } catch (Exception e) {
            logger.error("Error creating Keycloak user", e);
            throw new RuntimeException("Error creating user", e);
        } finally {
            if (keycloak != null) {
                keycloak.close();
            }
        }
    }
    // Helper method to extract user ID from response
    private String extractUserIdFromResponse(Response response) {
        String location = response.getHeaderString("Location");
        if (location == null) {
            throw new RuntimeException("Failed to extract user ID from response");
        }
        return location.substring(location.lastIndexOf("/") + 1);
    }

    private void validateUserInput(String username, String email, String password) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }

    private Keycloak createKeycloakInstance() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakConfig.getServerUrl())
                .realm(keycloakConfig.getRealm())
                .clientId(keycloakConfig.getClientId())
                .clientSecret(keycloakConfig.getClientSecret())
                .grantType("client_credentials")
                .build();
    }

    private UserRepresentation createUserRepresentation(
            String username, String email, String firstName,
            String lastName, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        user.setEmailVerified(false);

        // Create credentials
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        user.setCredentials(Collections.singletonList(credential));

        return user;
    }

    private boolean isUserExists(Keycloak keycloak, String username, String email) {
        List<UserRepresentation> existingUsersByUsername = keycloak.realm(keycloakConfig.getRealm())
                .users()
                .search(username, null, null, email, 0, 1);

        List<UserRepresentation> existingUsersByEmail = keycloak.realm(keycloakConfig.getRealm())
                .users()
                .search(null, null, null, email, 0, 1);

        return !existingUsersByUsername.isEmpty() || !existingUsersByEmail.isEmpty();
    }

    // Custom exception for user already exists
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }
    // Method to get access token
    public String getAccessToken() {
        Keycloak keycloak = null;
        try {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakConfig.getServerUrl())
                    .realm(keycloakConfig.getRealm())
                    .clientId(keycloakConfig.getClientId())
                    .clientSecret(keycloakConfig.getClientSecret())
                    .grantType("client_credentials")
                    .build();

            return keycloak.tokenManager().getAccessToken().getToken();
        } catch (Exception e) {
            logger.error("Error retrieving access token", e);
            throw new RuntimeException("Failed to retrieve access token", e);
        } finally {
            if (keycloak != null) {
                keycloak.close();
            }
        }
    }

    // Method to retrieve user data by ID
//    public UserDTO getUserById(String userId) {
//        Keycloak keycloak = null;
//        try {
//            keycloak = createKeycloakInstance();
//
//            // Retrieve user representation
//            UserRepresentation userRepresentation = keycloak.realm(keycloakConfig.getRealm())
//                    .users()
//                    .get(userId)
//                    .toRepresentation();
//
//            // Convert to DTO
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(userRepresentation.getId());
//            userDTO.setUsername(userRepresentation.getUsername());
//            userDTO.setEmail(userRepresentation.getEmail());
//            userDTO.setFirstName(userRepresentation.getFirstName());
//            userDTO.setLastName(userRepresentation.getLastName());
//            userDTO.setEnabled(userRepresentation.isEnabled());
//
//            // Optional: Add any additional attributes
//            if (userRepresentation.getAttributes() != null) {
//                userDTO.setAttributes(userRepresentation.getAttributes());
//            }
//
//            return userDTO;
//        } catch (NotFoundException e) {
//            logger.error("User not found with ID: {}", userId);
//            throw new UserNotFoundException("User not found with ID: " + userId);
//        } catch (Exception e) {
//            logger.error("Error retrieving user", e);
//            throw new RuntimeException("Failed to retrieve user", e);
//        } finally {
//            if (keycloak != null) {
//                keycloak.close();
//            }
//        }
//    }
    // Method to retrieve user data by UserName
    public UserDTO getUserByUserName(String username) {
        Keycloak keycloak = null;
        try {
            keycloak = createKeycloakInstance();

            // Search for the user by username
            List<UserRepresentation> users = keycloak.realm(keycloakConfig.getRealm())
                    .users()
                    .search(username, null, null, null, 0, 1);

            if (users.isEmpty()) {
                throw new UserNotFoundException("User not found with username: " + username);
            }

            // Get the first matching user
            UserRepresentation userRepresentation = users.get(0);

            // Convert to DTO
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userRepresentation.getId());
            userDTO.setUsername(userRepresentation.getUsername());
            userDTO.setEmail(userRepresentation.getEmail());
            userDTO.setFirstName(userRepresentation.getFirstName());
            userDTO.setLastName(userRepresentation.getLastName());
            userDTO.setEnabled(userRepresentation.isEnabled());

            // Optional: Add any additional attributes
            if (userRepresentation.getAttributes() != null) {
                userDTO.setAttributes(userRepresentation.getAttributes());
            }

            return userDTO;
        } catch (Exception e) {
            logger.error("Error retrieving user by username: {}", username, e);
            throw new RuntimeException("Failed to retrieve user by username", e);
        } finally {
            if (keycloak != null) {
                keycloak.close();
            }
        }
    }


    // DTO for user data
    public static class UserDTO {
        private String id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private boolean enabled;
        private Map<String, List<String>> attributes;

        // Getters and setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
        public Map<String, List<String>> getAttributes() { return attributes; }
        public void setAttributes(Map<String, List<String>> attributes) { this.attributes = attributes; }
    }
    //update user by username
    public void updateUserByUsername(String username, UserDTO updatedUser) {
        Keycloak keycloak = null;
        try {
            keycloak = createKeycloakInstance();

            // Search for the user by username
            List<UserRepresentation> users = keycloak.realm(keycloakConfig.getRealm())
                    .users()
                    .search(username, null, null, null, 0, 1);

            if (users.isEmpty()) {
                throw new UserNotFoundException("User not found with username: " + username);
            }

            // Retrieve the first user (assuming usernames are unique)
            UserRepresentation userRepresentation = users.get(0);

            // Update fields
            if (StringUtils.isNotBlank(updatedUser.getEmail())) {
                userRepresentation.setEmail(updatedUser.getEmail());
            }
            if (StringUtils.isNotBlank(updatedUser.getFirstName())) {
                userRepresentation.setFirstName(updatedUser.getFirstName());
            }
            if (StringUtils.isNotBlank(updatedUser.getLastName())) {
                userRepresentation.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.isEnabled() != userRepresentation.isEnabled()) {
                userRepresentation.setEnabled(updatedUser.isEnabled());
            }

            // Update user in Keycloak
            keycloak.realm(keycloakConfig.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .update(userRepresentation);

            logger.info("User with username: {} updated successfully", username);
        } catch (Exception e) {
            logger.error("Error updating Keycloak user", e);
            throw new RuntimeException("Failed to update user", e);
        } finally {
            if (keycloak != null) {
                keycloak.close();
            }
        }
    }
    //delete user by username
    public void deleteUserByUsername(String username) {
        Keycloak keycloak = null;
        try {
            keycloak = createKeycloakInstance();

            // Search for the user by username
            List<UserRepresentation> users = keycloak.realm(keycloakConfig.getRealm())
                    .users()
                    .search(username, null, null, null, 0, 1);

            if (users.isEmpty()) {
                throw new UserNotFoundException("User not found with username: " + username);
            }

            // Retrieve the first user (assuming usernames are unique)
            UserRepresentation userRepresentation = users.get(0);

            // Delete the user
            keycloak.realm(keycloakConfig.getRealm())
                    .users()
                    .get(userRepresentation.getId())
                    .remove();

            logger.info("User with username: {} deleted successfully", username);
        } catch (Exception e) {
            logger.error("Error deleting Keycloak user", e);
            throw new RuntimeException("Failed to delete user", e);
        } finally {
            if (keycloak != null) {
                keycloak.close();
            }
        }
    }
    //login user by username /email and password
    public String login(String usernameOrEmail, String password) {
        try {
            // Build Keycloak client for Direct Access Grant (Password Grant)
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakConfig.getServerUrl())
                    .realm(keycloakConfig.getRealm())
                    .clientId(keycloakConfig.getClientId())
                    .clientSecret(keycloakConfig.getClientSecret())
                    .grantType(OAuth2Constants.PASSWORD) // Use password grant type
                    .username(usernameOrEmail)
                    .password(password)
                    .build();

            // Request an access token
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            logger.info("User {} authenticated successfully", usernameOrEmail);

            return tokenResponse.getToken(); // Return the access token
        } catch (Exception e) {
            logger.error("Authentication failed for user {}", usernameOrEmail, e);
            throw new RuntimeException("Invalid credentials or login failed", e);
        }
    }


    // Custom exceptions
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}