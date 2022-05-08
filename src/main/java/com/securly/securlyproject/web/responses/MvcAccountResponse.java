package com.securly.securlyproject.web.responses;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class MvcAccountResponse {

    private Long id;

    private String name;

    private String defaultTimeZone;

    private String workflowState;
}
