package com.example.realestate.offre.entity;


<<<<<<< HEAD
import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> 688507999801bca93855486daac82b630257a191
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "immobiliers")  // Spécifie que cette entité sera stockée dans la collection "immobiliers"
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Immobilier {
    @Id
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