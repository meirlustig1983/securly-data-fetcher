package com.securly.securlyproject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "canvas.service")
@Configuration
public class CanvasProperties {

    private String instanceUrl;

    private String requestCanvasAccessTemplate;

    private String redirectUrl;

    private String accessTokenPath;

    private String accountsRequestPath;

    private String courseAccountsRequestPath;

    private String coursesRequestPath;

    private String sectionsRequestPath;

    private String ClientId;

    private String clientSecret;
}