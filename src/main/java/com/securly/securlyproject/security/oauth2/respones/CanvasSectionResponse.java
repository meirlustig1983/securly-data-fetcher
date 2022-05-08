package com.securly.securlyproject.security.oauth2.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Date;

@Accessors(chain = true)
@Data
@ToString
public class CanvasSectionResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("start_at")
    private Date startAt;

    @JsonProperty("end_at")
    private Date endAt;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("restrict_enrollments_to_section_dates")
    private String restrictEnrollmentsToSectionDates;

    @JsonProperty("nonxlist_course_id")
    private Long nonxlistCourseId;

    @JsonProperty("sis_section_id")
    private String sisSectionId;

    @JsonProperty("sis_course_id")
    private String sisCourseId;

    @JsonProperty("integration_id")
    private String integrationId;

    @JsonProperty("sis_import_id")
    private String sisImportId;
}
