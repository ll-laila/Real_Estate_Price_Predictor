package com.example.realestate.offre.mapper;

import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.request.ImmobilierRequest;
import com.example.realestate.offre.response.ImmobilierResponse;
import org.springframework.stereotype.Component;

@Component
public class ImmobilierMapper {

    // Method to map ImmobilierRequest to Immobilier entity
    public static Immobilier toImmobilier(ImmobilierRequest request) {
        if (request == null) {
            return null;
        }

        return Immobilier.builder()
                .id(request.id())
                .title(request.title())
                .images(request.images())
                .bedroom(request.bedroom())
                .bathroom(request.bathroom())
                .price(request.price())
                .address(request.address())
                .city(request.city())
                .description(request.description())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .type(request.type())                  // Mapping new fields
                .property(request.property())          // Mapping new fields
                .utilities(request.utilities())        // Mapping new fields
                .petPolicy(request.petPolicy())        // Mapping new fields
                .incomePolicy(request.incomePolicy())  // Mapping new fields
                .size(request.size())                  // Mapping new fields
                .schoolDistance(request.schoolDistance()) // Mapping new fields
                .busDistance(request.busDistance())    // Mapping new fields
                .restaurantDistance(request.restaurantDistance()) // Mapping new fields
                .build();
    }

    // Method to map Immobilier entity to ImmobilierResponse (optional)
    public static ImmobilierResponse fromImmobilier(Immobilier immobilier) {
        return new ImmobilierResponse(
                immobilier.getId(),
                immobilier.getTitle(),
                immobilier.getImages(),
                immobilier.getBedroom(),
                immobilier.getBathroom(),
                immobilier.getPrice(),
                immobilier.getAddress(),
                immobilier.getCity(),
                immobilier.getLatitude(),
                immobilier.getLongitude(),
                immobilier.getDescription(),
                immobilier.getType(),                 // Include new fields if needed
                immobilier.getProperty(),             // Include new fields if needed
                immobilier.getUtilities(),            // Include new fields if needed
                immobilier.getPetPolicy(),            // Include new fields if needed
                immobilier.getIncomePolicy(),         // Include new fields if needed
                immobilier.getSize(),                 // Include new fields if needed
                immobilier.getSchoolDistance(),       // Include new fields if needed
                immobilier.getBusDistance(),          // Include new fields if needed
                immobilier.getRestaurantDistance()    // Include new fields if needed
        );
    }
}