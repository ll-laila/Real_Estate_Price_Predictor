package com.example.realestate.payment.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Plan {
    @Id
    private Long id;
    private String name;
    private double price;
    private int maxPrediction;
    private String description;
}
