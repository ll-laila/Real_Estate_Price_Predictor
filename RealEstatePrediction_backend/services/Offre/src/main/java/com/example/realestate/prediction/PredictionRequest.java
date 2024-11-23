package com.example.realestate.prediction;


public record PredictionRequest(
        String city,
        String date_to_predict,
        int current_price
) {

}
