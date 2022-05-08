package com.securly.securlyproject.converters;

import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.security.oauth2.respones.CanvasCourseResponse;
import com.securly.securlyproject.web.responses.MvcCourseResponse;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    public MvcCourseResponse convert(Course course) {
        return new MvcCourseResponse()
            .setId(course.getId())
            .setName(course.getName())
            .setCourseCode(course.getCourseCode())
            .setWorkflowState(course.getWorkflowState())
            .setAccountId(course.getAccountId())
            .setStartAt(course.getStartAt())
            .setTimeZone(course.getTimeZone());
    }

    public Course convert(CanvasCourseResponse canvasAccountResponse) {
        return new Course()
            .setId(canvasAccountResponse.getId())
            .setName(canvasAccountResponse.getName())
            .setAccountId(canvasAccountResponse.getAccountId())
            .setUuid(canvasAccountResponse.getUuid())
            .setStartAt(canvasAccountResponse.getStartAt())
            .setGradingStandardId(canvasAccountResponse.getGradingStandardId())
            .setIsPublic(canvasAccountResponse.getIsPublic())
            .setCreatedAt(canvasAccountResponse.getCreatedAt())
            .setCourseCode(canvasAccountResponse.getCourseCode())
            .setDefaultView(canvasAccountResponse.getDefaultView())
            .setRootAccountId(canvasAccountResponse.getRootAccountId())
            .setEnrollmentTermId(canvasAccountResponse.getEnrollmentTermId())
            .setLicense(canvasAccountResponse.getLicense())
            .setEndAt(canvasAccountResponse.getEndAt())
            .setPublicSyllabus(canvasAccountResponse.getPublicSyllabus())
            .setPublicSyllabusToAuth(canvasAccountResponse.getPublicSyllabusToAuth())
            .setStorageQuotaMb(canvasAccountResponse.getStorageQuotaMb())
            .setIsPublicToAuthUsers(canvasAccountResponse.getIsPublicToAuthUsers())
            .setApplyAssignmentGroupWeights(canvasAccountResponse.getApplyAssignmentGroupWeights())
            .setTimeZone(canvasAccountResponse.getTimeZone())
            .setBlueprint(canvasAccountResponse.getBlueprint())
            .setSisCourseId(canvasAccountResponse.getSisCourseId())
            .setSisImportId(canvasAccountResponse.getSisImportId())
            .setIntegrationId(canvasAccountResponse.getIntegrationId())
            .setHideFinalGrades(canvasAccountResponse.getHideFinalGrades())
            .setWorkflowState(canvasAccountResponse.getWorkflowState())
            .setRestrictEnrollmentsToCourseDates(canvasAccountResponse.getRestrictEnrollmentsToCourseDates());
    }
}