package com.example.realestate.payment.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDTO {
    private String id;
    private PlanDTO plan;
    private String userId;
}
