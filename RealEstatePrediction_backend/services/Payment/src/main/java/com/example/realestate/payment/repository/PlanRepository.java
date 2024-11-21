package com.example.realestate.payment.repository;

import com.example.realestate.payment.entity.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanRepository extends MongoRepository<Plan, String> {
    Optional<Plan> findByName(String name);
}
