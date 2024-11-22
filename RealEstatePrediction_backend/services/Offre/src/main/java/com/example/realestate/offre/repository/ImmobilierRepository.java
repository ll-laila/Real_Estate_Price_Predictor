package com.example.realestate.offre.repository;


import com.example.realestate.offre.entity.Immobilier;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImmobilierRepository extends MongoRepository<Immobilier, String> {

}
