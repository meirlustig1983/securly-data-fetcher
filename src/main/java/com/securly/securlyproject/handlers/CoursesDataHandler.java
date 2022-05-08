package com.securly.securlyproject.handlers;

import com.securly.securlyproject.config.CanvasProperties;
import com.securly.securlyproject.converters.CourseConverter;
import com.securly.securlyproject.data.DataFacade;
import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import com.securly.securlyproject.security.oauth2.respones.CanvasCourseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Order(2)
@Component
public class CoursesDataHandler implements DataHandler {

    private final OAuth2RestTemplate restTemplate;

    private final DataFacade facade;

    private final CanvasProperties properties;

    private final CourseConverter converter;

    @Autowired
    public CoursesDataHandler(OAuth2RestTemplate restTemplate, DataFacade facade, CanvasProperties properties, CourseConverter converter) {
        this.restTemplate = restTemplate;
        this.facade = facade;
        this.properties = properties;
        this.converter = converter;
    }

    @Override
    public void apply(Sync sync) {
        List<CanvasCourseResponse> courses = restTemplate.getCoursesFromCanvas(properties.getInstanceUrl() + properties.getCoursesRequestPath());
        log.info("courses objects received from canvas-api, data: {}", courses);
        Map<Long, Course> coursesMap = facade.getAllCoursesFromDb().stream().collect(Collectors.toMap(Course::getId, Function.identity()));

        sync.setCoursesExists(courses.size());

        courses.forEach(canvasCourseResponse -> {
            Course course = converter.convert(canvasCourseResponse);
            if (coursesMap.containsKey(course.getId())) {
                log.info("course already exists, course-id: {}, course-name: {}", canvasCourseResponse.getId(), canvasCourseResponse.getName());
                Course oldCourse = coursesMap.get(course.getId());
                if (!oldCourse.equals(course)) {
                    log.info("course has been updated, course-id: {}, course-name: {}", canvasCourseResponse.getId(), canvasCourseResponse.getName());
                    facade.saveToDb(course);
                    sync.setCoursesUpdated(sync.getCoursesUpdated() + 1);
                }
                coursesMap.remove(course.getId());
            } else {
                log.info("new course has been saved, course-id: {}, course-name: {}", canvasCourseResponse.getId(), canvasCourseResponse.getName());
                facade.saveToDb(course);
                sync.setCoursesNew(sync.getCoursesNew() + 1);
            }
        });

        if (coursesMap.isEmpty()) {
            sync.setAccountsDeleted(0);
        } else {
            coursesMap.values().forEach(facade::deleteFromDb);
            sync.setCoursesDeleted(coursesMap.size());
        }
    }
}