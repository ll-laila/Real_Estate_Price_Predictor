package com.example.realestate.offre.repository;


import com.example.realestate.offre.entity.Immobilier;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======
>>>>>>> 688507999801bca93855486daac82b630257a191
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImmobilierRepository extends MongoRepository<Immobilier, String> {

}