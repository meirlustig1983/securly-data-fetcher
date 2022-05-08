package com.securly.securlyproject.web.responses;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@Accessors(chain = true)
@ToString
public class MvcSectionResponse {

    private Long id;

    private String name;

    private Long courseId;

    private Date createdAt;

    private Long totalStudents;
}
