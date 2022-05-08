package com.securly.securlyproject.security.oauth2.requests;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors(chain = true)
public class AccessTokenRequest {

    private String grantType;

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String code;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"grant_type\"").append(":").append("\"").append(grantType).append('\"').append(",");
        sb.append("\"client_id\"").append(":").append("\"").append(clientId).append('\"').append(",");
        sb.append("\"client_secret\"").append(":").append("\"").append(clientSecret).append('\"').append(",");
        sb.append("\"redirect_uri\"").append(":").append("\"").append(redirectUri).append('\"').append(",");
        sb.append("\"code\"").append(":").append("\"").append(code).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
