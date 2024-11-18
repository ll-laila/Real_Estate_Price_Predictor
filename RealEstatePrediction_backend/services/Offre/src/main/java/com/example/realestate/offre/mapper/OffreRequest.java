package com.example.realestate.offre.mapper;

import com.example.realestate.offre.entity.Immobilier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OffreRequest(
        String id,
        @NotNull(message = "Offer immobilier is required")
        Immobilier immobilier,
        @NotNull(message = "Offer UserId is required")
        String userId

) {
}
