package com.example.realestate.payment.response;

public record SubscriptionResponse(
        String id,
        String planId,
        String userId,
        int nbrPrediction
) {
}

