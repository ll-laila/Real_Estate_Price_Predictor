package com.example.realestate.offre.service;

import com.example.realestate.User.UserClient;
import com.example.realestate.User.UserResponse;
import com.example.realestate.offre.dto.DTOConverter;
import com.example.realestate.offre.dto.OffreDTO;
import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.entity.Offre;
import com.example.realestate.offre.mapper.OffreMapper;
import com.example.realestate.offre.mapper.OffreRequest;
import com.example.realestate.offre.mapper.OffreResponse;
import com.example.realestate.offre.repository.ImmobilierRepository;
import com.example.realestate.offre.repository.OffreRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OffreServiceImpl implements OffreService{
    private final OffreRepository offreRepository;
    private final ImmobilierRepository immobilierRepository;
    private final UserClient userClient;
    private final UserClient user;

    public ResponseEntity<String> testMyUser(){
        return user.testUser();
    }
    @Override
    public OffreResponse createOfferWithImmobilier(OffreRequest offreRequest) {
        var user = this.userClient.findById(offreRequest.userId());
        // Create and save immobilier
        Immobilier immobilier = OffreMapper.toImmobilierEntity(offreRequest.immobilier());
        Immobilier savedImmobilier = immobilierRepository.save(immobilier);

        // Create and save offre
        Offre offre = OffreMapper.toOffreEntity(offreRequest);
        offre.setUserId(offreRequest.userId());
        offre.setImmobilier(savedImmobilier);
        offre.setDateDePublication(LocalDate.now());  // Set date of publication to current date
        offre.setDateDeUpdate(LocalDate.now());  // Set date of update to current date
        Offre savedOffre = offreRepository.save(offre);

        return OffreMapper.toResponse(savedOffre);
    }

    @Override
    public OffreResponse updateOfferWithImmobilier(String offreId, OffreRequest updatedRequest, String userId) {
        // Fetch existing offer
        Offre existingOffre = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offer not found with id: " + offreId));

        // Verify ownership
        if (!existingOffre.getUserId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this offer.");
        }

        // Update the offer with the new details from the request
        existingOffre.setDateDeUpdate(LocalDate.now());
        existingOffre.setUserId(updatedRequest.userId());

        // Update immobilier
        Immobilier updatedImmobilier = OffreMapper.toImmobilierEntity(updatedRequest.immobilier());
        if (updatedImmobilier != null) {
            updatedImmobilier.setId(existingOffre.getImmobilier().getId()); // Preserve original ID
            immobilierRepository.save(updatedImmobilier); // Save updated immobilier
            existingOffre.setImmobilier(updatedImmobilier); // Associate updated immobilier
        }

        // Save updated offer
        Offre savedOffre = offreRepository.save(existingOffre);
        return OffreMapper.toResponse(savedOffre);
    }

    @Override
    public void deleteOfferWithImmobilier(String offreId) {
        Offre offer = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offer not found with id: " + offreId));

        Immobilier immobilier = offer.getImmobilier();
        offreRepository.delete(offer);

        if (immobilier != null) {
            immobilierRepository.delete(immobilier);
        }
    }

    @Override
    public OffreResponse getOfferWithImmobilier(String offerId) {
        Offre offre = offreRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found with id: " + offerId));

        return OffreMapper.toResponse(offre);
    }

    @Override
    public List<OffreResponse> getAllOffers() {
        List<Offre> offreList = offreRepository.findAll();

        return offreList.stream()
                .map(OffreMapper::toResponse)
                .collect(Collectors.toList());
    }

}
