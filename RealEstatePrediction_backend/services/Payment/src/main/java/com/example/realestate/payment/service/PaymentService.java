package com.example.realestate.payment.service;

import com.example.realestate.User.UserRequest;
import com.example.realestate.User.UserResponse;
import com.example.realestate.payment.entity.Plan;
import com.example.realestate.payment.entity.Subscription;
import com.example.realestate.payment.mapper.PaymentMapper;
import com.example.realestate.payment.repository.PlanRepository;
import com.example.realestate.payment.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PlanRepository planRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PlanRepository planRepository, SubscriptionRepository subscriptionRepository, PaymentMapper paymentMapper) {
        this.planRepository = planRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.paymentMapper = paymentMapper;
    }

    @Transactional
    public UserResponse subscribeUser(UserRequest userRequest, String planId) {
        // Récupération du plan
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // Vérification du nombre de prédictions restantes
        if (plan.getMaxPrediction() <= 0) {
            throw new RuntimeException("No more predictions available for this plan");
        }

        // Création de l'abonnement
        Subscription subscription = Subscription.builder()
                .user(userRequest)
                .plan(plan)
                .build();

        subscriptionRepository.save(subscription);

        // Mise à jour du plan
        plan.setMaxPrediction(plan.getMaxPrediction() - 1);
        planRepository.save(plan);

        // Retourner la réponse
        return paymentMapper.toUserResponse(subscription);
    }
}
