package com.example.realestate.User;

import lombok.*;

@Data
@Builder
public class UserResponse {
    private String userId;
    private String planName;
    private int remainingPredictions;
}
