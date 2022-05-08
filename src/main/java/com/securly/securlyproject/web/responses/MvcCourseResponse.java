package com.securly.securlyproject.web.responses;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@Accessors(chain = true)
@ToString
public class MvcCourseResponse {

    private Long id;

    private String name;

    private String courseCode;

    private String workflowState;

    private Long accountId;

    private Date startAt;

    private String timeZone;
}
