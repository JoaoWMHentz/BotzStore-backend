package com.kinematech.kinematech_backend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShippingService {

    private final RestTemplate restTemplate;

    public ShippingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String calculateShipping(Double weight, Double length, Double width, Double height, int quantity) {
        String url = "https://api.melhorenvio.com.br/v2/me/shipping/calculate";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer SUA_CHAVE_DE_API");
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("weight", weight * quantity);
        requestBody.put("length", length);
        requestBody.put("width", width);
        requestBody.put("height", height);
        requestBody.put("quantity", quantity);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }
}