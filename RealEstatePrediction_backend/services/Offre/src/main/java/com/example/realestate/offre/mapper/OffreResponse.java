package com.example.realestate.offre.mapper;

import com.example.realestate.offre.entity.Immobilier;

import java.time.LocalDate;

public record OffreResponse(
        String id,
        Immobilier immobilier,
        String userId,
        LocalDate dateDePublication,
        LocalDate dateDeUpdate
) {
}
