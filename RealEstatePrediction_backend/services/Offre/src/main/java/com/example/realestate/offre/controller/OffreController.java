package com.example.realestate.offre.controller;

import com.example.realestate.offre.dto.OffreDTO;
import com.example.realestate.offre.mapper.OffreRequest;
import com.example.realestate.offre.mapper.OffreResponse;
import com.example.realestate.offre.service.OffreService;
import com.example.realestate.offre.service.OffreServiceold;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offres")
@RequiredArgsConstructor
public class OffreController {

    private final OffreService offreService;
    // Créer une offre avec un immobilier
    @PostMapping("/create")
    public ResponseEntity<OffreResponse> createOfferWithImmobilier(@RequestBody @Valid OffreRequest request) {
        if (request == null || request.immobilier() == null) {
            throw new RuntimeException("Offre or Immobilier data is missing in the request body");
        }
        OffreResponse createdOffer = offreService.createOfferWithImmobilier(request);
        return ResponseEntity.ok(createdOffer);
    }
    @PutMapping("/{offreId}/user/{userId}")
    public ResponseEntity<OffreResponse> updateOfferWithImmobilier(
            @PathVariable String offreId,
            @RequestBody OffreRequest request,
            @PathVariable String userId) {
        try {
            // Call the service to update the offer
            OffreResponse updatedOffer = offreService.updateOfferWithImmobilier(offreId, request, userId);

            // Return the updated offer in the response
            return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Return a 403 Forbidden if there was an issue with the update
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);  // 403 Unauthorized
        } catch (Exception e) {
            // Return a 404 Not Found if the offer is not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // 404 Not Found
        }
    }

    @DeleteMapping("/{offreId}")
    public ResponseEntity<String> deleteOfferWithImmobilier(@PathVariable Long offreId) {
        try {
            offreService.deleteOfferWithImmobilier(String.valueOf(offreId));
            return new ResponseEntity<>("Offer and its associated immobilier deleted successfully", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // 404 Offer not found
        }
    }
    // Récupérer une offre avec son immobilier
    @GetMapping("/{offerId}")
    public ResponseEntity<OffreResponse> getOfferWithImmobilier(@PathVariable String offerId) {
        try {
            OffreResponse offer = offreService.getOfferWithImmobilier(offerId);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // 404 Not Found
        }
    }
    // Récupérer toutes les offres
    @GetMapping
    public ResponseEntity<List<OffreResponse>> getAllOffers() {
        List<OffreResponse> offers = offreService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }




    @GetMapping("/testOffre")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service Offre is working!");
    }

    @GetMapping("/MyUser")
    public ResponseEntity<String> testMyUser() {
        return offreService.testMyUser();
    }



}
