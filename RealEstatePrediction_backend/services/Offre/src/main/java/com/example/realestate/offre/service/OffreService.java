package com.example.realestate.offre.service;

import com.example.realestate.User.UserClient;
import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.entity.Offre;
import com.example.realestate.offre.exception.BusinessException;
import com.example.realestate.offre.mapper.ImmobilierMapper;
import com.example.realestate.offre.mapper.OffreMapper;
import com.example.realestate.offre.request.OffreRequest;
import com.example.realestate.offre.repository.ImmobilierRepository;
import com.example.realestate.offre.repository.OffreRepository;
import com.example.realestate.offre.response.OffreResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OffreService {

    private final OffreRepository offreRepository;
    private final ImmobilierRepository immobilierRepository;
    private final UserClient userClient;

    public OffreService(OffreRepository offreRepository,
                        ImmobilierRepository immobilierRepository,
                        ImmobilierMapper immobilierMapper,
                        OffreMapper offreMapper,
                        UserClient userClient) {
        this.offreRepository = offreRepository;
        this.immobilierRepository = immobilierRepository;
        this.userClient = userClient;
    }


    @Transactional(rollbackFor = Exception.class)
    public String createOfferWithImmobilier(OffreRequest offreRequest) {

        var user = this.userClient.findById(offreRequest.userId());
        if (user == null) {
            log.error("Utilisateur non trouvé pour l'ID : {}", offreRequest.userId());
            throw new BusinessException("Utilisateur introuvable pour l'ID fourni.");
        }

        Immobilier immobilier = ImmobilierMapper.toImmobilier(offreRequest.immobilierRequest());
        Immobilier savedImmobilier = immobilierRepository.save(immobilier);

        Offre offre = OffreMapper.toOffre(offreRequest);
        offre.setUserId(offreRequest.userId());
        offre.setImmobilier(savedImmobilier);
        offre.setDateDePublication(LocalDate.now());

        Offre savedOffre = offreRepository.save(offre);

        return savedOffre.getId();
    }




    @Transactional(rollbackFor = Exception.class)
    public String updateOfferWithImmobilier(OffreRequest offreRequest) {

        Offre existingOffre = offreRepository.findById(offreRequest.id())
                .orElseThrow(() -> new BusinessException("Offre introuvable pour l'ID : " + offreRequest.id()));

        var user = this.userClient.findById(offreRequest.userId());
        if (user == null) {
            log.error("Utilisateur non trouvé pour l'ID : {}", offreRequest.userId());
            throw new BusinessException("Utilisateur introuvable pour l'ID fourni.");
        }

        Immobilier immobilier;
        if (offreRequest.immobilierRequest().id() != null) {

            immobilier = immobilierRepository.findById(offreRequest.immobilierRequest().id())
                    .orElseThrow(() -> new BusinessException("Immobilier introuvable pour l'ID : " + offreRequest.immobilierRequest().id()));
            immobilier = ImmobilierMapper.toImmobilier(offreRequest.immobilierRequest());

            immobilier.setTitle(offreRequest.immobilierRequest().title());
            immobilier.setImg(offreRequest.immobilierRequest().img());
            immobilier.setPrice(offreRequest.immobilierRequest().price());
            immobilier.setBedroom(offreRequest.immobilierRequest().bedroom());
            immobilier.setBathroom(offreRequest.immobilierRequest().bathroom());
            immobilier.setAddress(offreRequest.immobilierRequest().address());
            immobilier.setCity(offreRequest.immobilierRequest().city());
            immobilier.setLatitude(offreRequest.immobilierRequest().latitude());
            immobilier.setLongitude(offreRequest.immobilierRequest().longitude());

        } else {
            immobilier = ImmobilierMapper.toImmobilier(offreRequest.immobilierRequest());
        }

        Immobilier savedImmobilier = immobilierRepository.save(immobilier);

        existingOffre.setImmobilier(savedImmobilier);
        existingOffre.setUserId(offreRequest.userId());
        existingOffre.setDateDePublication(offreRequest.dateDePublication());
        Offre updatedOffre = offreRepository.save(existingOffre);

        return updatedOffre.getId();
    }



    
    public void deleteOfferWithImmobilier(String offreId) {
        Offre offer = offreRepository.findById(offreId)
                .orElseThrow(() -> new BusinessException("Offer not found with id: " + offreId));
        Immobilier immobilier = offer.getImmobilier();
        offreRepository.delete(offer);

        if (immobilier != null) {
            immobilierRepository.delete(immobilier);
        }
    }


    
    public OffreResponse getOfferWithImmobilier(String offerId) {
        Offre offre = offreRepository.findById(offerId)
                .orElseThrow(() -> new BusinessException("Offer not found with id: " + offerId));
        return OffreMapper.fromOffre(offre);
    }


    
    public List<OffreResponse> getAllOffers() {
        List<Offre> offreList = offreRepository.findAll();

        return offreList.stream()
                .map(OffreMapper::fromOffre)
                .collect(Collectors.toList());
    }


}
