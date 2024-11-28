package com.example.realestate.offre.repository;

import com.example.realestate.offre.entity.Offre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OffreRepository extends MongoRepository<Offre, String> {
    List<Offre> findOffreByUserId(String userId);

}