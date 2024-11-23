package com.example.realestate.offre.request;


public record ImmobilierRequest (
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
