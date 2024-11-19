package com.example.realestate.offre.dto;


import com.example.realestate.User.UserClient;
import com.example.realestate.User.UserResponse;
import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.entity.Offre;
import com.example.realestate.offre.service.OffreService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DTOConverter {
    private static UserClient userClient;
    public static OffreDTO toOffreDTO(Offre offre) {
        UserResponse user = userClient.findById(offre.getUserId());
        OffreDTO dto = new OffreDTO();
        dto.setId(offre.getId());
        dto.setDateDePublication(offre.getDateDePublication());
        dto.setImmobilier(toImmobilierDTO(offre.getImmobilier()));
        dto.setUserId(offre.getUserId());//recuperer id user qui cree offre
        dto.setUserResponse(user);
        return dto;
    }
    public static Offre toOffreEntity(OffreDTO dto) {
        Offre offre = new Offre();
        offre.setDateDePublication(dto.getDateDePublication());
        offre.setImmobilier(toImmobilierEntity(dto.getImmobilier()));
        return offre;
    }
    public static ImmobilierDTO toImmobilierDTO(Immobilier immobilier) {
        ImmobilierDTO dto = new ImmobilierDTO();
        dto.setId(immobilier.getId());
        dto.setTitle(immobilier.getTitle());
        dto.setImg(immobilier.getImg());
        dto.setBedroom(immobilier.getBedroom());
        dto.setBathroom(immobilier.getBathroom());
        dto.setPrice(immobilier.getPrice());
        dto.setAddress(immobilier.getAddress());
        dto.setLatitude(immobilier.getLatitude());
        dto.setLongitude(immobilier.getLongitude());
        return dto;
    }
    public static Immobilier toImmobilierEntity(ImmobilierDTO dto) {
        Immobilier immobilier = new Immobilier();
        immobilier.setTitle(dto.getTitle());
        immobilier.setImg(dto.getImg());
        immobilier.setBedroom(dto.getBedroom());
        immobilier.setBathroom(dto.getBathroom());
        immobilier.setPrice(dto.getPrice());
        immobilier.setAddress(dto.getAddress());
        immobilier.setLatitude(dto.getLatitude());
        immobilier.setLongitude(dto.getLongitude());
        return immobilier;
    }




}
