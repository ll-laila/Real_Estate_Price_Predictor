package com.example.realestate.offre.response;


public record ImmobilierResponse (
        String id,
        String title ,
        String img ,
        int bedroom ,
        int bathroom ,
        double price ,
        String address ,
        String city ,
        double latitude ,
        double longitude
){
}

