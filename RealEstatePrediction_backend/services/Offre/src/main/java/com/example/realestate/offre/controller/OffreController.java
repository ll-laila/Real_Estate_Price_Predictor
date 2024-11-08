package com.example.realestate.offre.controller;

import com.example.realestate.offre.service.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offres")
@RequiredArgsConstructor
public class OffreController {

    private final OffreService service;


    @GetMapping("/testOffre")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service Offre is working!");
    }

    @GetMapping("/MyUser")
    public ResponseEntity<String> testMyUser() {
        return service.testMyUser();
    }

}
