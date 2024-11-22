package com.example.realestate.offre.dto;
import lombok.Data;
@Data
public class ImmobilierDTO {
    private String id;
    private String title;
    private String img;
    private int bedroom;
    private int bathroom;
    private double price;
    private String address;
    private double latitude;
    private double longitude;


}
