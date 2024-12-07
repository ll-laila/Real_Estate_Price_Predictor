package com.example.realestate.User.controller;

import com.example.realestate.User.dto.UserDTO;
import com.example.realestate.User.service.KeycloakUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
// @CrossOrigin(origins = "http://localhost:5173")
public class KeycloakUserController {
    @Autowired
    private KeycloakUserService keycloakUserService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserCreationRequest request) {
        String userId = keycloakUserService.createUser(
                request.getUsername(),
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                request.getPassword()
        );

        Map<String, String> response = new HashMap<>();
        response.put("user ID", userId);
        response.put("message", "User created successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // DTO for user creation
    public static class UserCreationRequest {
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String password;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
    // Endpoint to retrieve access token
    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> getAccessToken() {
        String token = keycloakUserService.getAccessToken();

        Map<String, String> response = new HashMap<>();
        response.put("access_token", token);
        response.put("token_type", "Bearer");

        return ResponseEntity.ok(response);
    }

    // Endpoint to get user by ID
//    @GetMapping("/{userId}")
//    public ResponseEntity<KeycloakUserService.UserDTO> getUserById(@PathVariable String userId) {
//        KeycloakUserService.UserDTO user = keycloakUserService.getUserById(userId);
//        return ResponseEntity.ok(user);
//    }
    // Endpoint to get user by UserName
    //try to use UserDTO in class separately
    //then use this methode in offre service
    //fix the front end
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String username) {
        UserDTO user = keycloakUserService.getUserByUserName(username);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        // Extract user from the authentication context
        UserDTO currentUser = keycloakUserService.getUserByUserName(principal.getName());
        return ResponseEntity.ok(currentUser);
    }
    @PutMapping("/{username}")
    public ResponseEntity<Map<String, String>> updateUserByUsername(
            @PathVariable String username,
            @RequestBody UserDTO updatedUser) {
        keycloakUserService.updateUserByUsername(username, updatedUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User updated successfully");
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<Map<String, String>> deleteUserByUsername(@PathVariable String username) {
        keycloakUserService.deleteUserByUsername(username);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response);
    }

    // Global Exception Handler
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(KeycloakUserService.UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUserNotFound(
                KeycloakUserService.UserNotFoundException ex) {
            ErrorResponse error = new ErrorResponse(
                    "USER_NOT_FOUND",
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND.value()
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        // Error Response DTO
        public static class ErrorResponse {
            private String code;
            private String message;
            private int status;

            // Constructor
            public ErrorResponse(String code, String message, int status) {
                this.code = code;
                this.message = message;
                this.status = status;
            }

            // Getters and setters
            public String getCode() { return code; }
            public void setCode(String code) { this.code = code; }
            public String getMessage() { return message; }
            public void setMessage(String message) { this.message = message; }
            public int getStatus() { return status; }
            public void setStatus(int status) { this.status = status; }
        }
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        String token = keycloakUserService.login(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("access_token", token);
        response.put("token_type", "Bearer");

        return ResponseEntity.ok(response);
    }

    // DTO for Login Request
    public static class LoginRequest {
        private String usernameOrEmail;
        private String password;

        // Getters and setters
        public String getUsernameOrEmail() {
            return usernameOrEmail;
        }

        public void setUsernameOrEmail(String usernameOrEmail) {
            this.usernameOrEmail = usernameOrEmail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
