package com.example.realestate.payment.mapper;

import com.example.realestate.payment.dto.PlanDTO;
import com.example.realestate.payment.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {
    public PlanDTO toDTO(Plan plan) {
        return PlanDTO.builder()
                .id(plan.getId())
                .name(plan.getName())
                .price(plan.getPrice())
                .maxPrediction(plan.getMaxPrediction())
                .description(plan.getDescription())
                .build();
    }

    public Plan toEntity(PlanDTO planDTO) {
        return Plan.builder()
                .id(planDTO.getId())
                .name(planDTO.getName())
                .price(planDTO.getPrice())
                .maxPrediction(planDTO.getMaxPrediction())
                .description(planDTO.getDescription())
                .build();
    }
}
