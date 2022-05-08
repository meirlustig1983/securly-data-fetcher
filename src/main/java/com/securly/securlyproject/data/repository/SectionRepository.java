package com.securly.securlyproject.data.repository;

import com.securly.securlyproject.data.model.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    List<Section> findAllByCourseId(long courseId);
}