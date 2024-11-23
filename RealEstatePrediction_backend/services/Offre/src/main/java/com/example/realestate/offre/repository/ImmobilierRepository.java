package com.example.realestate.offre.repository;


import com.example.realestate.offre.entity.Immobilier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImmobilierRepository extends MongoRepository<Immobilier, String> {

}
