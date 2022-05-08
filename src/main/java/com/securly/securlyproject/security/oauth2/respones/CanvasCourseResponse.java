package com.securly.securlyproject.security.oauth2.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.List;

@Accessors(chain = true)
@Data
@ToString
public class CanvasCourseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("start_at")
    private Date startAt;

    @JsonProperty("grading_standard_id")
    private Long gradingStandardId;

    @JsonProperty("is_public")
    private Boolean isPublic;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("course_code")
    private String courseCode;

    @JsonProperty("default_view")
    private String defaultView;

    @JsonProperty("root_account_id")
    private Long rootAccountId;

    @JsonProperty("enrollment_term_id")
    private Long enrollmentTermId;

    @JsonProperty("license")
    private String license;

    @JsonProperty("end_at")
    private Date endAt;

    @JsonProperty("public_syllabus")
    private Boolean publicSyllabus;

    @JsonProperty("public_syllabus_to_auth")
    private Boolean publicSyllabusToAuth;

    @JsonProperty("storage_quota_mb")
    private Long storageQuotaMb;

    @JsonProperty("is_public_to_auth_users")
    private Boolean isPublicToAuthUsers;

    @JsonProperty("apply_assignment_group_weights")
    private Boolean applyAssignmentGroupWeights;

    @JsonProperty("calendar")
    private Calendar calendar;

    @JsonProperty("time_zone")
    private String timeZone;

    @JsonProperty("blueprint")
    private Boolean blueprint;

    @JsonProperty("sis_course_id")
    private String sisCourseId;

    @JsonProperty("sis_import_id")
    private String sisImportId;

    @JsonProperty("integration_id")
    private String integrationId;

    @JsonProperty("enrollments")
    private List<Enrollment> enrollments;

    @JsonProperty("hide_final_grades")
    private Boolean hideFinalGrades;

    @JsonProperty("workflow_state")
    private String workflowState;

    @JsonProperty("restrict_enrollments_to_course_dates")
    private Boolean restrictEnrollmentsToCourseDates;

    @JsonProperty("overridden_course_visibility")
    private String overriddenCourseVisibility;
}
