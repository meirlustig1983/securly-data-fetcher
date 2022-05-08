package com.securly.securlyproject.security.oauth2;

import com.securly.securlyproject.log.EnableLog;
import com.securly.securlyproject.security.oauth2.requests.AccessTokenRequest;
import com.securly.securlyproject.security.oauth2.requests.RefreshTokenRequest;
import com.securly.securlyproject.security.oauth2.respones.AccessTokenResponse;
import com.securly.securlyproject.security.oauth2.respones.CanvasAccountResponse;
import com.securly.securlyproject.security.oauth2.respones.CanvasCourseResponse;
import com.securly.securlyproject.security.oauth2.respones.CanvasSectionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Slf4j
public class OAuth2RestTemplate extends RestTemplate {

    private final String accessTokenUri;

    private final String clientId;

    private final String clientSecret;

    private final String redirectUri;

    private String accessToken;

    private String refreshToken;

    private Calendar tokenTimeStamp;

    public OAuth2RestTemplate(String accessTokenUri, String clientId, String clientSecret, String redirectUri) {
        super();
        this.accessTokenUri = accessTokenUri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    @EnableLog
    public void refreshTokenRequest() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest()
            .setGrantType("refresh_token")
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRefreshToken(refreshToken);

        HttpEntity<String> request = new HttpEntity<>(refreshTokenRequest.toString(), getHttpHeaders());

        AccessTokenResponse response = postForObject(accessTokenUri, request, AccessTokenResponse.class);
        if (response != null) {
            log.info("refresh token request");
            accessToken = response.getAccessToken();
            tokenTimeStamp = Calendar.getInstance();
            tokenTimeStamp.add(Calendar.SECOND, response.getExpiresIn());
        }
    }

    @EnableLog
    public void accessTokenRequest(String code) {
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest()
            .setGrantType("authorization_code")
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .setCode(code);

        HttpEntity<String> request = new HttpEntity<>(accessTokenRequest.toString(), getHttpHeaders());
        AccessTokenResponse response = postForObject(accessTokenUri, request, AccessTokenResponse.class);
        log.info("access token request");
        if (response != null) {
            accessToken = response.getAccessToken();
            refreshToken = response.getRefreshToken();
            tokenTimeStamp = Calendar.getInstance();
            tokenTimeStamp.add(Calendar.SECOND, response.getExpiresIn());
        }
    }

    @EnableLog("url: %s")
    public List<CanvasAccountResponse> getAccountsFromCanvas(String url) {
        CanvasAccountResponse[] accounts = super.getForObject(url + "?access_token=" + accessToken, CanvasAccountResponse[].class);
        if (accounts != null) {
            return Arrays.asList(accounts);
        } else {
            return Collections.emptyList();
        }
    }

    @EnableLog("url: %s")
    public List<CanvasCourseResponse> getCoursesFromCanvas(String url) {
        CanvasCourseResponse[] courses = super.getForObject(url + "?access_token=" + accessToken, CanvasCourseResponse[].class);
        if (courses != null) {
            return Arrays.asList(courses);
        } else {
            return Collections.emptyList();
        }
    }

    @EnableLog("url: %s")
    public List<CanvasSectionResponse> getSectionFromCanvas(String url) {
        CanvasSectionResponse[] sections = super.getForObject(url + "?access_token=" + accessToken, CanvasSectionResponse[].class);
        if (sections != null) {
            return Arrays.asList(sections);
        } else {
            return Collections.emptyList();
        }
    }

    public boolean validateToken() {
        if (tokenTimeStamp != null) {
            validateTimeStamp();
            return true;
        } else {
            return false;
        }
    }

    private void validateTimeStamp() {
        if (tokenTimeStamp.before(Calendar.getInstance())) {
            log.info("access token is invalid");
            refreshTokenRequest();
        } else {
            log.info("access token is valid");
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}