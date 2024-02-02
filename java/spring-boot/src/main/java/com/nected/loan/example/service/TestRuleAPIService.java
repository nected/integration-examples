package com.nected.loan.example.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.GsonBuilder;
import com.nected.loan.example.configuration.NectedAppConfig;
import com.nected.loan.example.utils.CustomHttpClientRequestFactory;


/**
 * This class is used to call the rule API
 * The rule API is used to get the output data based on the input data
 * The input data is sent to the rule API in the form of a payload
 * The output data is received from the rule API in the form of a map
 * The rule API is called using the RestTemplate
 * The RestTemplate is used to make the HTTP request to the rule API
 * The RestTemplate is configured to bypass the SSL certificate validation if the SSL bypass flag is enabled
 * The RestTemplate is also configured to add the API key to the request header
 * The API key is used to authenticate the request
 * The output data is returned to the caller
 * If the response status is not OK, then null is returned
 * If there is an exception while calling the rule API, then null is returned
 * 
 * The RestTemplate is configured to use the custom request factory if the SSL bypass flag is enabled
 */
@Service
public class TestRuleAPIService {

    @Autowired
	private NectedAppConfig config;
    final RestTemplate restTemplate = new RestTemplate();

    /**
     * This method is used to get the rule payload
     * The rule payload is a JSON string containing the input data
     * 
     * 
     * @return The rule payload
     */
    private String getRulePayload() {
        // Create the payload
        Map<String, Object> params = new HashMap<>();
        params.put("environment", "production");
        params.put("Loan_Amount", 100000);

        return new GsonBuilder().create().toJson(params);
    }

    /**
     * This method is used to call the rule API
     * The rule API is called using the RestTemplate
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public Map<String, Object> testRuleAPI() throws IOException, InterruptedException {
        // If the SSL bypass is enabled, then we need to use the custom request factory
        // to bypass the SSL certificate validation
        if (config.isSSLBypass()) {
            // Set the custom request factory
            restTemplate.setRequestFactory(new CustomHttpClientRequestFactory());
        }

        // Add the API key to the request header
        // This is required to authenticate the request
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("x-api-key", config.getApiKey());
            return execution.execute(request, body);
        });

        // Call the rule API
        // The response will be a map containing the output data
        ResponseEntity<Map> response = restTemplate.postForEntity(config.getRuleUrl(), getRulePayload(), Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> outputData = response.getBody();
            // Return the output data
            return outputData;
        } else {
            // If the response status is not OK, then return null
            return null;
        }
    }
}
