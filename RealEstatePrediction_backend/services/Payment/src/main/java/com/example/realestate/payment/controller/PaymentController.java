package com.example.realestate.payment.controller;

import com.example.realestate.User.UserRequest;
import com.example.realestate.User.UserResponse;
import com.example.realestate.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/testPayment")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service Payment is working!");
    }


    private final PaymentService paymentService;

    @PostMapping("/subscribe/{planId}")
    public ResponseEntity<UserResponse> subscribeUser(
            @PathVariable String planId,
            @RequestBody UserRequest userRequest) {

        UserResponse userResponse = paymentService.subscribeUser(userRequest, planId);
        return ResponseEntity.ok(userResponse);
    }
}
