package com.nected.loan.example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "nected")
@Data
public class NectedAppConfig {
    
    @Data
    public class Rule {
        private String scheme;
        private String host;
        private String path;
        private String ruleId;

        public String getRuleUrl() {
            return scheme + "://" + host + "/" + path + "/" + ruleId;
        }
    }

    @Bean
    @ConfigurationProperties(prefix = "nected.rule")
    public Rule rule() {
        return new Rule();
    }

}
