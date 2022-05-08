package com.securly.securlyproject.handlers;

import com.securly.securlyproject.config.CanvasProperties;
import com.securly.securlyproject.converters.SectionConverter;
import com.securly.securlyproject.data.DataFacade;
import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.data.model.Section;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import com.securly.securlyproject.security.oauth2.respones.CanvasSectionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class SectionsDataHandlerTest {

    private static final String INSTANCE_URL = "http://127.0.0.1";

    private static final String SECTIONS_REQUEST_PATH = "/api/v1/courses/%s/sections";

    @Mock
    private OAuth2RestTemplate restTemplate;

    @Mock
    private DataFacade facade;

    @Mock
    private CanvasProperties properties;

    @Mock
    private SectionConverter converter;

    private SectionDataHandler handler;

    @BeforeEach
    void setup() {
        handler = new SectionDataHandler(restTemplate, facade, properties, converter);
    }

    @Test
    public void apply_NewSection() {

        Sync sync = new Sync();

        Course course = new Course()
            .setId(1L)
            .setName("test");

        CanvasSectionResponse canvasSectionResponse = new CanvasSectionResponse()
            .setId(1L)
            .setName("test");

        Section section = new Section()
            .setId(1L)
            .setName("test");

        String sectionsRequestPath = String.format(SECTIONS_REQUEST_PATH, 1L);

        when(facade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(course));

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getSectionsRequestPath()).thenReturn(SECTIONS_REQUEST_PATH);
        when(restTemplate.getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath)).thenReturn(Collections.singletonList(canvasSectionResponse));
        when(converter.convert(canvasSectionResponse)).thenReturn(section);
        when(facade.getAllSectionsByCourseIdFromDb(1L)).thenReturn(Collections.emptyList());

        handler.apply(sync);

        assertEquals(1, sync.getSectionsExists());
        assertEquals(1, sync.getSectionsNew());
        assertEquals(0, sync.getSectionsDeleted());
        assertEquals(0, sync.getSectionsDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getSectionsRequestPath();
        verify(restTemplate).getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath);
        verify(converter).convert(canvasSectionResponse);
        verify(facade).getAllCoursesFromDb();
        verify(facade).getAllSectionsByCourseIdFromDb(1L);
        verify(facade).saveToDb(section);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_UpdatedSection() {

        Sync sync = new Sync();

        Course course = new Course()
            .setId(1L)
            .setName("test");

        CanvasSectionResponse canvasSectionResponse = new CanvasSectionResponse()
            .setId(1L)
            .setName("test");

        Section section = new Section()
            .setId(1L)
            .setName("test");

        Section oldSection = new Section()
            .setId(1L)
            .setName("test1");

        String sectionsRequestPath = String.format(SECTIONS_REQUEST_PATH, 1L);

        when(facade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(course));

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getSectionsRequestPath()).thenReturn(SECTIONS_REQUEST_PATH);
        when(restTemplate.getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath)).thenReturn(Collections.singletonList(canvasSectionResponse));
        when(converter.convert(canvasSectionResponse)).thenReturn(section);
        when(facade.getAllSectionsByCourseIdFromDb(1L)).thenReturn(Collections.singletonList(oldSection));

        handler.apply(sync);

        assertEquals(1, sync.getSectionsExists());
        assertEquals(0, sync.getSectionsNew());
        assertEquals(1, sync.getSectionsUpdated());
        assertEquals(0, sync.getSectionsDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getSectionsRequestPath();
        verify(restTemplate).getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath);
        verify(converter).convert(canvasSectionResponse);
        verify(facade).getAllCoursesFromDb();
        verify(facade).getAllSectionsByCourseIdFromDb(1L);
        verify(facade).saveToDb(section);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_ExistsSection() {

        Sync sync = new Sync();

        Course course = new Course()
            .setId(1L)
            .setName("test");

        CanvasSectionResponse canvasSectionResponse = new CanvasSectionResponse()
            .setId(1L)
            .setName("test");

        Section section = new Section()
            .setId(1L)
            .setName("test");

        String sectionsRequestPath = String.format(SECTIONS_REQUEST_PATH, 1L);

        when(facade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(course));

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getSectionsRequestPath()).thenReturn(SECTIONS_REQUEST_PATH);
        when(restTemplate.getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath)).thenReturn(Collections.singletonList(canvasSectionResponse));
        when(converter.convert(canvasSectionResponse)).thenReturn(section);
        when(facade.getAllSectionsByCourseIdFromDb(1L)).thenReturn(Collections.singletonList(section));

        handler.apply(sync);

        assertEquals(1, sync.getSectionsExists());
        assertEquals(0, sync.getSectionsNew());
        assertEquals(0, sync.getSectionsUpdated());
        assertEquals(0, sync.getSectionsDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getSectionsRequestPath();
        verify(restTemplate).getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath);
        verify(converter).convert(canvasSectionResponse);
        verify(facade).getAllCoursesFromDb();
        verify(facade).getAllSectionsByCourseIdFromDb(1L);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_DeletedSection() {

        Sync sync = new Sync();

        Course course = new Course()
            .setId(1L)
            .setName("test");

        CanvasSectionResponse canvasSectionResponse = new CanvasSectionResponse()
            .setId(1L)
            .setName("test");

        Section section = new Section()
            .setId(1L)
            .setName("test");

        Section oldSection = new Section()
            .setId(2L)
            .setName("test1");

        String sectionsRequestPath = String.format(SECTIONS_REQUEST_PATH, 1L);

        when(facade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(course));

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getSectionsRequestPath()).thenReturn(SECTIONS_REQUEST_PATH);
        when(restTemplate.getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath)).thenReturn(Collections.singletonList(canvasSectionResponse));
        when(converter.convert(canvasSectionResponse)).thenReturn(section);
        when(facade.getAllSectionsByCourseIdFromDb(1L)).thenReturn(Collections.singletonList(oldSection));

        handler.apply(sync);

        assertEquals(1, sync.getSectionsExists());
        assertEquals(1, sync.getSectionsNew());
        assertEquals(0, sync.getSectionsUpdated());
        assertEquals(1, sync.getSectionsDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getSectionsRequestPath();
        verify(restTemplate).getSectionFromCanvas(INSTANCE_URL + sectionsRequestPath);
        verify(converter).convert(canvasSectionResponse);
        verify(facade).getAllCoursesFromDb();
        verify(facade).getAllSectionsByCourseIdFromDb(1L);
        verify(facade).saveToDb(section);
        verify(facade).deleteFromDb(oldSection);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }
}