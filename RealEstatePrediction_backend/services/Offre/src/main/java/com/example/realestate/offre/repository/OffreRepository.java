package com.example.realestate.offre.repository;

import com.example.realestate.offre.entity.Offre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OffreRepository extends MongoRepository<Offre, String> {

}