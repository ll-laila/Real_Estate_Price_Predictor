package com.example.realestate.offre.dto;

import com.example.realestate.User.UserResponse;
import lombok.Data;

import java.time.LocalDate;
@Data
public class OffreDTO {
    private String id;
    private LocalDate dateDePublication;
    private ImmobilierDTO immobilier;
    private String userId;
    private UserResponse userResponse;

}
