package com.example.realestate.offre.mapper;

import com.example.realestate.offre.entity.Immobilier;
<<<<<<< HEAD
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

=======
import jakarta.validation.constraints.NotNull;

>>>>>>> 688507999801bca93855486daac82b630257a191
public record OffreRequest(
        String id,
        @NotNull(message = "Offer immobilier is required")
        Immobilier immobilier,
        @NotNull(message = "Offer UserId is required")
        String userId

) {
}
