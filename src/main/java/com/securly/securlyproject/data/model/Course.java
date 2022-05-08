package com.securly.securlyproject.data.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Data
@Accessors(chain = true)
@ToString
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id")
    private Long id;

    @Column(name = "sis_course_id")
    private String sisCourseId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "integration_id")
    private String integrationId;

    @Column(name = "sis_import_id")
    private String sisImportId;

    @Column(name = "name")
    private String name;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "workflow_state")
    private String workflowState;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "root_account_id")
    private Long rootAccountId;

    @Column(name = "enrollment_term_id")
    private Long enrollmentTermId;

    @Column(name = "grading_periods")
    private String gradingPeriods;

    @Column(name = "grading_standard_id")
    private Long gradingStandardId;

    @Column(name = "grade_passback_setting")
    private String gradePassbackSetting;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "locale")
    private String locale;

    @Column(name = "enrollments")
    private String enrollments;

    @Column(name = "total_students")
    private Long totalStudents;

    @Column(name = "calendar")
    private String calendar;

    @Column(name = "default_view")
    private String defaultView;

    @Column(name = "syllabus_body")
    private String syllabusBody;

    @Column(name = "needs_grading_count")
    private Long needsGradingCount;

    @Column(name = "term")
    private String term = null;

    @Column(name = "course_progress")
    private String courseProgress;

    @Column(name = "apply_assignment_group_weights")
    private Boolean applyAssignmentGroupWeights;

    @Column(name = "permissions")
    private String permissions;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "is_public_to_auth_users")
    private Boolean isPublicToAuthUsers;

    @Column(name = "public_syllabus")
    private Boolean publicSyllabus;

    @Column(name = "public_syllabus_to_auth")
    private Boolean publicSyllabusToAuth;

    @Column(name = "public_description")
    private String publicDescription;

    @Column(name = "storage_quota_mb")
    private Long storageQuotaMb;

    @Column(name = "storage_quota_used_mb")
    private Long storageQuotaUsedMb;

    @Column(name = "hide_final_grades")
    private Boolean hideFinalGrades;

    @Column(name = "license")
    private String license;

    @Column(name = "allow_student_assignment_edits")
    private Boolean allowStudentAssignmentEdits;

    @Column(name = "allow_wiki_comments")
    private Boolean allowWikiComments;

    @Column(name = "allow_student_forum_attachments")
    private Boolean allowStudentForumAttachments;

    @Column(name = "open_enrollment")
    private Boolean openEnrollment;

    @Column(name = "self_enrollment")
    private Boolean selfEnrollment;

    @Column(name = "restrict_enrollments_to_course_dates")
    private Boolean restrictEnrollmentsToCourseDates;

    @Column(name = "course_format")
    private String courseFormat;

    @Column(name = "access_restricted_by_date")
    private Boolean accessRestrictedByDate;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "blueprint")
    private Boolean blueprint;

    @Column(name = "blueprint_restrictions")
    private String blueprintRestrictions;

    @Column(name = "blueprint_restrictions_by_object_type")
    private String blueprintRestrictionsByObjectType;

    @Column(name = "template")
    private Boolean template;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;

        String startAtStringA = startAt == null ? null : startAt.toString();
        String startAtStringB = course.startAt == null ? null : course.startAt.toString();

        String endAtStringA = endAt == null ? null : endAt.toString();
        String endAtStringB = course.endAt == null ? null : course.endAt.toString();

        String createdAtStringA = createdAt == null ? null : createdAt.toString();
        String createdAtStringB = course.createdAt == null ? null : course.createdAt.toString();

        return Objects.equals(id, course.id) && Objects.equals(sisCourseId, course.sisCourseId) && Objects.equals(uuid, course.uuid) && Objects.equals(integrationId, course.integrationId) && Objects.equals(sisImportId, course.sisImportId) && Objects.equals(name, course.name) && Objects.equals(courseCode, course.courseCode) && Objects.equals(originalName, course.originalName) && Objects.equals(workflowState, course.workflowState) && Objects.equals(accountId, course.accountId) && Objects.equals(rootAccountId, course.rootAccountId) && Objects.equals(enrollmentTermId, course.enrollmentTermId) && Objects.equals(gradingPeriods, course.gradingPeriods) && Objects.equals(gradingStandardId, course.gradingStandardId) && Objects.equals(gradePassbackSetting, course.gradePassbackSetting) && Objects.equals(createdAtStringA, createdAtStringB) && Objects.equals(startAtStringA, startAtStringB) && Objects.equals(endAtStringA, endAtStringB) && Objects.equals(locale, course.locale) && Objects.equals(enrollments, course.enrollments) && Objects.equals(totalStudents, course.totalStudents) && Objects.equals(calendar, course.calendar) && Objects.equals(defaultView, course.defaultView) && Objects.equals(syllabusBody, course.syllabusBody) && Objects.equals(needsGradingCount, course.needsGradingCount) && Objects.equals(term, course.term) && Objects.equals(courseProgress, course.courseProgress) && Objects.equals(applyAssignmentGroupWeights, course.applyAssignmentGroupWeights) && Objects.equals(permissions, course.permissions) && Objects.equals(isPublic, course.isPublic) && Objects.equals(isPublicToAuthUsers, course.isPublicToAuthUsers) && Objects.equals(publicSyllabus, course.publicSyllabus) && Objects.equals(publicSyllabusToAuth, course.publicSyllabusToAuth) && Objects.equals(publicDescription, course.publicDescription) && Objects.equals(storageQuotaMb, course.storageQuotaMb) && Objects.equals(storageQuotaUsedMb, course.storageQuotaUsedMb) && Objects.equals(hideFinalGrades, course.hideFinalGrades) && Objects.equals(license, course.license) && Objects.equals(allowStudentAssignmentEdits, course.allowStudentAssignmentEdits) && Objects.equals(allowWikiComments, course.allowWikiComments) && Objects.equals(allowStudentForumAttachments, course.allowStudentForumAttachments) && Objects.equals(openEnrollment, course.openEnrollment) && Objects.equals(selfEnrollment, course.selfEnrollment) && Objects.equals(restrictEnrollmentsToCourseDates, course.restrictEnrollmentsToCourseDates) && Objects.equals(courseFormat, course.courseFormat) && Objects.equals(accessRestrictedByDate, course.accessRestrictedByDate) && Objects.equals(timeZone, course.timeZone) && Objects.equals(blueprint, course.blueprint) && Objects.equals(blueprintRestrictions, course.blueprintRestrictions) && Objects.equals(blueprintRestrictionsByObjectType, course.blueprintRestrictionsByObjectType) && Objects.equals(template, course.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sisCourseId, uuid, integrationId, sisImportId, name, courseCode, originalName, workflowState, accountId, rootAccountId, enrollmentTermId, gradingPeriods, gradingStandardId, gradePassbackSetting, createdAt, startAt, endAt, locale, enrollments, totalStudents, calendar, defaultView, syllabusBody, needsGradingCount, term, courseProgress, applyAssignmentGroupWeights, permissions, isPublic, isPublicToAuthUsers, publicSyllabus, publicSyllabusToAuth, publicDescription, storageQuotaMb, storageQuotaUsedMb, hideFinalGrades, license, allowStudentAssignmentEdits, allowWikiComments, allowStudentForumAttachments, openEnrollment, selfEnrollment, restrictEnrollmentsToCourseDates, courseFormat, accessRestrictedByDate, timeZone, blueprint, blueprintRestrictions, blueprintRestrictionsByObjectType, template);
    }
}
