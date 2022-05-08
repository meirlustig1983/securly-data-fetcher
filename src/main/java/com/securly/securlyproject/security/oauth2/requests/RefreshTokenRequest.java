package com.securly.securlyproject.security.oauth2.requests;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RefreshTokenRequest {

    private String grantType;

    private String clientId;

    private String clientSecret;

    private String refreshToken;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"grant_type\"").append(":").append("\"").append(grantType).append('\"').append(",");
        sb.append("\"client_id\"").append(":").append("\"").append(clientId).append('\"').append(",");
        sb.append("\"client_secret\"").append(":").append("\"").append(clientSecret).append('\"').append(",");
        sb.append("\"refresh_token\"").append(":").append("\"").append(refreshToken).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
