package com.example.realestate.prediction;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "prediction-service",
        url = "http://127.0.0.1:8000/api/predict/"
)
public interface PredictionClient {

    @PostMapping
    PredictionResponse getPrediction(@RequestBody PredictionRequest request);

}
