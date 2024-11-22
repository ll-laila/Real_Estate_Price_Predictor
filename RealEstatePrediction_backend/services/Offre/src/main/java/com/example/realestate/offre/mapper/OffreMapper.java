package com.example.realestate.offre.mapper;

import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.entity.Offre;
public class OffreMapper {

    // Convert OffreRequest to Offre Entity
    public static Offre toOffreEntity(OffreRequest request) {
        if (request == null) {
            return null;
        }
        return Offre.builder()
                .id(request.id())
                .userId(request.userId())
                .immobilier(toImmobilierEntity(request.immobilier()))
                .build();
    }

    // Convert Immobilier to Immobilier Entity
    public static Immobilier toImmobilierEntity(Immobilier immobilier) {
        if (immobilier == null) {
            return null;
        }
        return Immobilier.builder()
                .id(immobilier.getId())
                .title(immobilier.getTitle())
                .img(immobilier.getImg())
                .bedroom(immobilier.getBedroom())
                .bathroom(immobilier.getBathroom())
                .price(immobilier.getPrice())
                .address(immobilier.getAddress())
                .latitude(immobilier.getLatitude())
                .longitude(immobilier.getLongitude())
                .build();
    }

    // Convert Offre Entity to OffreResponse
    public static OffreResponse toResponse(Offre offre) {
        if (offre == null) {
            return null;
        }
        return new OffreResponse(
                offre.getId(),
                toImmobilierResponse(offre.getImmobilier()),
                offre.getUserId(),
                offre.getDateDePublication(),
                offre.getDateDeUpdate()
        );
    }

    // Convert Immobilier Entity to ImmobilierResponse
    public static Immobilier toImmobilierResponse(Immobilier immobilier) {
        if (immobilier == null) {
            return null;
        }
        return new Immobilier(
                immobilier.getId(),
                immobilier.getTitle(),
                immobilier.getImg(),
                immobilier.getBedroom(),
                immobilier.getBathroom(),
                immobilier.getPrice(),
                immobilier.getAddress(),
                immobilier.getLatitude(),
                immobilier.getLongitude()
        );
    }
}
