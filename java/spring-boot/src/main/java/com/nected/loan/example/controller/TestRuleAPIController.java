package com.nected.loan.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nected.loan.example.service.TestRuleAPIService;

@RestController
public class TestRuleAPIController {
    @Autowired
    private TestRuleAPIService testRuleAPIService;


    @GetMapping("/test")
    public Map<String, Object> testRuleAPI() {
        try {
            return testRuleAPIService.testRuleAPI();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
