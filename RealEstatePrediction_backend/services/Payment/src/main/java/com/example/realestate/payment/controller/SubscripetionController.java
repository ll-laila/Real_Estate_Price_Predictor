package com.example.realestate.payment.controller;

import com.example.realestate.payment.entity.Subscription;
import com.example.realestate.payment.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/api/v1/plan")
public class SubscripetionController {
    private final SubscriptionService subscriptionService;

    public SubscripetionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Subscription> create(@RequestBody Subscription item) {
        return ResponseEntity.ok(subscriptionService.create(item));
    }
}
