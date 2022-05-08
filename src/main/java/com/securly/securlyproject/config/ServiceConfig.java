package com.securly.securlyproject.config;

import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(CanvasProperties properties) {
        return new OAuth2RestTemplate(properties.getInstanceUrl() + properties.getAccessTokenPath(), properties.getClientId(), properties.getClientSecret(), properties.getRedirectUrl());
    }
}
