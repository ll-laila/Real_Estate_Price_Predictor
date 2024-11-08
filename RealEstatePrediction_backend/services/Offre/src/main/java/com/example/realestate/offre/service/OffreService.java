package com.example.realestate.offre.service;


import com.example.realestate.User.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OffreService {

    private final UserClient user;

    public ResponseEntity<String> testMyUser(){
        return user.testUser();
    }
}
