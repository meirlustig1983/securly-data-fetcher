package com.securly.securlyproject.data.repository;

import com.securly.securlyproject.data.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAllByAccountId(long accountId);
}