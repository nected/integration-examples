package com.nected.loan.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource("classpath:nected.properties")
@Data
public class NectedAppConfig {
    @Value("${nected.apiKey}")
    private String apiKey;

    @Value("${nected.rule.scheme}")
    private String scheme;

    @Value("${nected.rule.host}")
    private String host;

    @Value("${nected.rule.path}")
    private String path;

    @Value("${nected.rule.ruleId}")
    private String ruleId;

    public String getRuleUrl() {
        return scheme + "://" + host +"/"+ path +"/" +ruleId;
    }

}
