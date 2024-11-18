package com.example.realestate.offre.repository;

import com.example.realestate.offre.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OffreRepository extends MongoRepository<Offre, String> {

}