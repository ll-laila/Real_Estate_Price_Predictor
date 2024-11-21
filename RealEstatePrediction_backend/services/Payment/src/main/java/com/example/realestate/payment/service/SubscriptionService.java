package com.example.realestate.payment.service;
import com.example.realestate.payment.entity.Subscription;
import com.example.realestate.payment.mapper.SubscriptionMapper;
import com.example.realestate.payment.repository.PlanRepository;
import com.example.realestate.payment.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, PlanRepository planRepository,
                               SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    public Subscription create(Subscription item) {
        return subscriptionRepository.save(item);
    }
}
