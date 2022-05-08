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
@Table(name = "Sections")
public class Section {

    @Id
    @Column(name = "section_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sis_section_id")
    private String sisSectionId;

    @Column(name = "integration_id")
    private String integrationId;

    @Column(name = "sis_import_id")
    private String sisImportId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "sis_course_id")
    private String sisCourseId;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "restrict_enrollments_to_section_dates")
    private String restrictEnrollmentsToSectionDates;

    @Column(name = "nonxlist_course_id")
    private Long nonxlistCourseId;

    @Column(name = "total_students")
    private Long totalStudents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;

        String startAtStringA = startAt == null ? null : startAt.toString();
        String startAtStringB = section.startAt == null ? null : section.startAt.toString();

        String endAtStringA = endAt == null ? null : endAt.toString();
        String endAtStringB = section.endAt == null ? null : section.endAt.toString();

        String createdAtStringA = createdAt == null ? null : createdAt.toString();
        String createdAtStringB = section.createdAt == null ? null : section.createdAt.toString();

        return Objects.equals(id, section.id) && Objects.equals(name, section.name) && Objects.equals(sisSectionId, section.sisSectionId) && Objects.equals(integrationId, section.integrationId) && Objects.equals(sisImportId, section.sisImportId) && Objects.equals(courseId, section.courseId) && Objects.equals(sisCourseId, section.sisCourseId) && Objects.equals(startAtStringA, startAtStringB) && Objects.equals(endAtStringA, endAtStringB) && Objects.equals(createdAtStringA, createdAtStringB) && Objects.equals(restrictEnrollmentsToSectionDates, section.restrictEnrollmentsToSectionDates) && Objects.equals(nonxlistCourseId, section.nonxlistCourseId) && Objects.equals(totalStudents, section.totalStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sisSectionId, integrationId, sisImportId, courseId, sisCourseId, startAt, endAt, createdAt, restrictEnrollmentsToSectionDates, nonxlistCourseId, totalStudents);
    }
}
