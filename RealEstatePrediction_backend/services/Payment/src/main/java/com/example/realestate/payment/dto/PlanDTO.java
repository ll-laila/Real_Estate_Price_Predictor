package com.example.realestate.payment.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDTO {
    private Long id;
    private String name;
    private double price;
    private int maxPrediction;
    private String description;
}
