package com.example.realestate.payment.repository;

import com.example.realestate.payment.entity.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
}
