package com.example.realestate.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "user-service",
        url = "${application.config.users-url}"
)
public interface UserClient {

    @GetMapping("/testUser")
    ResponseEntity<String> testUser();
}
