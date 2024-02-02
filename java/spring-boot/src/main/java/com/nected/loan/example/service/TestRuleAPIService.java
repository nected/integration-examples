package com.nected.loan.example.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class TestRuleAPIService {
    private static final String RULE_API = "https://nected-31.nected.io/nected/rule/64db4f1eecb6304b9427ee51";
    private static final String API_KEY = "YOUR_API_KEY"; // If authentication is enabled
    final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> TestRuleAPI() throws IOException, InterruptedException {
        // Create a map to store the rule input parameters
        Map<String, Object> params = new HashMap<>();
        // Map<String, Object> response = new HashMap<>();
        params.put("environment", "production");
        params.put("Loan_Amount", 100000);
        Gson gson = new GsonBuilder().create();
        String payload = gson.toJson(params);

        // RestTemplate restTemplate = new RestTemplate();
        // restTemplate.getInterceptors().add((request, body, execution) -> {
        //     request.getHeaders().add("x-api-key", API_KEY);
        //     return execution.execute(request, body);
        // });
        ResponseEntity<Map> response = restTemplate.postForEntity(RULE_API, payload, Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> outputData = response.getBody();
            return outputData;
        } else {
            return null;
        }
    }
}
