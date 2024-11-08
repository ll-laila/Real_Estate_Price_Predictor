package com.example.realestate.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @GetMapping("/testPayment")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service Payment is working!");
    }
}
