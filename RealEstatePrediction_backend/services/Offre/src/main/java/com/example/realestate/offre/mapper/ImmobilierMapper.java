package com.example.realestate.offre.mapper;


import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.request.ImmobilierRequest;
import com.example.realestate.offre.response.ImmobilierResponse;
import org.springframework.stereotype.Component;

@Component
public class ImmobilierMapper {
    public static Immobilier toImmobilier(ImmobilierRequest request) {
        if (request == null) {
            return null;
        }

        return Immobilier.builder()
                .id(request.id())
                .title(request.title())
                .img(request.img())
                .bedroom(request.bedroom())
                .bathroom(request.bathroom())
                .price(request.price())
                .address(request.address())
                .city(request.city())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .build();
    }

    public static ImmobilierResponse fromImmobilier(Immobilier immobilier) {

        return new ImmobilierResponse(
                immobilier.getId(),
                immobilier.getTitle(),
                immobilier.getImg(),
                immobilier.getBedroom(),
                immobilier.getBathroom(),
                immobilier.getPrice(),
                immobilier.getAddress(),
                immobilier.getCity(),
                immobilier.getLatitude(),
                immobilier.getLongitude()
                );
    }


}
