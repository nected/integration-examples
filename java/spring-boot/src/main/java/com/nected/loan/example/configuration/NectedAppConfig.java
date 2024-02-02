package com.nected.loan.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * This class is used to read the application properties
 * The application properties are used to configure the rule API
 */
@Component
@PropertySource("classpath:nected.properties")
@Data
public class NectedAppConfig {
    // The API key is used to authenticate the request
    @Value("${nected.apiKey}")
    private String apiKey;

    // The rule API URL
    @Value("${nected.rule.scheme}")
    private String scheme;

    // The rule API host
    @Value("${nected.rule.host}")
    private String host;

    // The rule API path
    @Value("${nected.rule.path}")
    private String path;

    // The rule ID
    @Value("${nected.rule.ruleId}")
    private String ruleId;

    // The SSL bypass flag
    // If this flag is set to true, then the SSL certificate validation will be bypassed
    @Value("${nected.bypassSsl}")
    private boolean SSLBypass;

    /**
     * This method is used to get the rule URL
     * The rule URL is constructed using the scheme, host, path and rule ID
     * @return The rule URL
     */
    public String getRuleUrl() {
        return scheme + "://" + host +"/"+ path +"/" +ruleId;
    }

}
