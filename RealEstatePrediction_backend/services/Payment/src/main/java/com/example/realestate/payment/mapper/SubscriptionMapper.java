package com.example.realestate.payment.mapper;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {
    private final PlanMapper planMapper;

    public SubscriptionMapper(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

//    public SubscriptionDTO toDTO(Subscription subscription) {
//        return SubscriptionDTO.builder()
//                .id(subscription.getId())
//                .plan(planMapper.toDTO(subscription.getPlan()))
//                .userId(subscription.getUser().getId()) // Assuming UserRequest has an `id` property
//                .build();
//    }

//    public Subscription toEntity(SubscriptionDTO subscriptionDTO) {
//
//        return Subscription.builder()
//                .id(subscriptionDTO.getId())
//                .plan(planMapper.toEntity(subscriptionDTO.getPlan()))
//                .build();
//    }
}
