package com.securly.securlyproject.converters;

import com.securly.securlyproject.data.model.Section;
import com.securly.securlyproject.security.oauth2.respones.CanvasSectionResponse;
import com.securly.securlyproject.web.responses.MvcSectionResponse;
import org.springframework.stereotype.Component;

@Component
public class SectionConverter {
    public MvcSectionResponse convert(Section section) {
        return new MvcSectionResponse()
            .setId(section.getId())
            .setName(section.getName())
            .setCourseId(section.getCourseId())
            .setCreatedAt(section.getCreatedAt())
            .setTotalStudents(section.getTotalStudents());
    }

    public Section convert(CanvasSectionResponse canvasSectionResponse) {
        return new Section()
            .setId(canvasSectionResponse.getId())
            .setCourseId(canvasSectionResponse.getCourseId())
            .setName(canvasSectionResponse.getName())
            .setStartAt(canvasSectionResponse.getStartAt())
            .setEndAt(canvasSectionResponse.getEndAt())
            .setCreatedAt(canvasSectionResponse.getCreatedAt())
            .setRestrictEnrollmentsToSectionDates(canvasSectionResponse.getRestrictEnrollmentsToSectionDates())
            .setNonxlistCourseId(canvasSectionResponse.getNonxlistCourseId())
            .setSisSectionId(canvasSectionResponse.getSisSectionId())
            .setIntegrationId(canvasSectionResponse.getIntegrationId())
            .setSisImportId(canvasSectionResponse.getSisImportId());
    }
}