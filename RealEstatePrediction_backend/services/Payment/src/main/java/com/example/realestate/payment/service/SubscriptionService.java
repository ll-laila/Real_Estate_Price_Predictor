package com.example.realestate.payment.service;

import com.example.realestate.payment.entity.Subscription;
import com.example.realestate.payment.exception.SubscriptionNotFoundException;
import com.example.realestate.payment.mapper.SubscriptionMapper;
import com.example.realestate.payment.repository.SubscriptionRepository;
import com.example.realestate.payment.request.SubscriptionRequest;
import com.example.realestate.payment.response.PlanResponse;
import com.example.realestate.payment.response.SubscriptionResponse;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;


@Service
public class SubscriptionService {

    @Autowired
    private PlanService planService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Transactional(rollbackFor = Exception.class)
    public String createSubscription(SubscriptionRequest request){

        if (request == null) return null;

        PlanResponse plan = planService.findByIdPlan(request.planId());
        if (plan == null) {
            throw new NotFoundException("Unknown Given plan");
        }

        Subscription subscription = Subscription.builder()
                .planId(request.planId())
                .userId(request.userId())
                .nbrPrediction(plan.maxPrediction())
                .build();

        var sub =  subscriptionRepository.save(subscription);

        return sub.getId();
    }


    public SubscriptionResponse getSubscriptionByIdUser(String idUser){
        return subscriptionRepository.findSubscriptionByUserId(idUser)
                .map(subscriptionMapper::fromSubscription)
                .orElseThrow(()-> new SubscriptionNotFoundException(format("Cannot find Subscription :: %s", idUser)));
    }


    public String updateUserSubscription(SubscriptionRequest subscriptionRequest) {

        var subExist = getSubscriptionByIdUser(subscriptionRequest.userId());
        if (subExist != null) {
            Subscription subscription = Subscription.builder()
                    .planId(subscriptionRequest.planId())
                    .userId(subscriptionRequest.userId())
                    .nbrPrediction(subscriptionRequest.nbrPrediction())
                    .build();

            var sub = subscriptionRepository.save(subscription);
            return sub.getId();
        }

        return null;
    }



}
