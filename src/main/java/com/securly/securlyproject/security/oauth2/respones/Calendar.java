package com.securly.securlyproject.security.oauth2.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Calendar {

    @JsonProperty("ics")
    private String ics;
}
