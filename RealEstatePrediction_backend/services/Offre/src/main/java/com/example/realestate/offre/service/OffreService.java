package com.example.realestate.offre.service;


import com.example.realestate.offre.mapper.OffreRequest;
import com.example.realestate.offre.mapper.OffreResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OffreService {
    ResponseEntity<String> testMyUser();
    OffreResponse createOfferWithImmobilier(OffreRequest offreRequest);

    OffreResponse updateOfferWithImmobilier(String offerId, OffreRequest updatedOffer, String userId);

    void deleteOfferWithImmobilier(String offerId);

    OffreResponse getOfferWithImmobilier(String offerId);

    List<OffreResponse> getAllOffers();
}
