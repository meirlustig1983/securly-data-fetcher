package com.securly.securlyproject.security.oauth2.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("global_id")
    private String globalId;

    @JsonProperty("effective_locale")
    private String effectiveLocale;
}