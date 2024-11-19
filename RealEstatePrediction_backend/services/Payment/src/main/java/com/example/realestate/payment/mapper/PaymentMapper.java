package com.example.realestate.payment.mapper;

import com.example.realestate.User.UserResponse;

import com.example.realestate.payment.entity.Subscription;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public UserResponse toUserResponse(Subscription subscription) {
        return UserResponse.builder()
                .userId(subscription.getUser().getUserId())
                .planName(subscription.getPlan().getName())
                .remainingPredictions(Integer.parseInt(String.valueOf(subscription.getPlan().getMaxPrediction())))
                .build();
    }
}
