package com.securly.securlyproject.handlers;

import com.securly.securlyproject.config.CanvasProperties;
import com.securly.securlyproject.converters.SectionConverter;
import com.securly.securlyproject.data.DataFacade;
import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.data.model.Section;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import com.securly.securlyproject.security.oauth2.respones.CanvasSectionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Order(3)
@Component
public class SectionDataHandler implements DataHandler {

    private final OAuth2RestTemplate restTemplate;

    private final DataFacade facade;

    private final CanvasProperties properties;

    private final SectionConverter converter;

    @Autowired
    public SectionDataHandler(OAuth2RestTemplate restTemplate, DataFacade facade, CanvasProperties properties, SectionConverter converter) {
        this.restTemplate = restTemplate;
        this.facade = facade;
        this.properties = properties;
        this.converter = converter;
    }

    @Override
    public void apply(Sync sync) {
        AtomicInteger existsSectionsCounter = new AtomicInteger(0);
        AtomicInteger newSectionsCounter = new AtomicInteger(0);
        AtomicInteger updatedSectionsCounter = new AtomicInteger(0);
        AtomicInteger deletedSectionsCounter = new AtomicInteger(0);

        List<Long> coursesIds = facade.getAllCoursesFromDb().stream().map(Course::getId).collect(Collectors.toList());
        String urlTemplate = properties.getSectionsRequestPath();
        coursesIds.forEach(courseId -> {
            String url = String.format(urlTemplate, courseId);
            List<CanvasSectionResponse> sections = restTemplate.getSectionFromCanvas(properties.getInstanceUrl() + url);
            log.info("sections objects received from canvas-api, data: {}", sections);
            Map<Long, Section> sectionsMap = facade.getAllSectionsByCourseIdFromDb(courseId).stream().collect(Collectors.toMap(Section::getId, Function.identity()));
            sections.forEach(canvasSectionResponse -> {
                existsSectionsCounter.incrementAndGet();
                Section section = converter.convert(canvasSectionResponse);
                if (sectionsMap.containsKey(section.getId())) {
                    log.info("section already exists, section-id: {}, section-name: {}", canvasSectionResponse.getId(), canvasSectionResponse.getName());
                    Section oldSection = sectionsMap.get(section.getId());
                    if (!oldSection.equals(section)) {
                        log.info("section has been updated, section-id: {}, section-name: {}", canvasSectionResponse.getId(), canvasSectionResponse.getName());
                        facade.saveToDb(section);
                        updatedSectionsCounter.incrementAndGet();
                    }
                    sectionsMap.remove(section.getId());
                } else {
                    log.info("new section has been saved, section-id: {}, section-name: {}", canvasSectionResponse.getId(), canvasSectionResponse.getName());
                    facade.saveToDb(section);
                    newSectionsCounter.incrementAndGet();
                }
            });

            if (!sectionsMap.isEmpty()) {
                sectionsMap.values().forEach(section -> {
                    deletedSectionsCounter.incrementAndGet();
                    facade.deleteFromDb(section);
                });
            }
        });
        sync.setSectionsExists(existsSectionsCounter.get());
        sync.setSectionsNew(newSectionsCounter.get());
        sync.setSectionsUpdated(updatedSectionsCounter.get());
        sync.setSectionsDeleted(deletedSectionsCounter.get());
    }
}