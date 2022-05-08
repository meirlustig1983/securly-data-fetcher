package com.securly.securlyproject.handlers;

import com.securly.securlyproject.config.CanvasProperties;
import com.securly.securlyproject.converters.CourseConverter;
import com.securly.securlyproject.data.DataFacade;
import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import com.securly.securlyproject.security.oauth2.respones.CanvasCourseResponse;
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
public class CoursesDataHandlerTest {

    private static final String INSTANCE_URL = "http://127.0.0.1";

    private static final String COURSES_REQUEST_PATH = "/api/v1/courses";

    @Mock
    private OAuth2RestTemplate restTemplate;

    @Mock
    private DataFacade facade;

    @Mock
    private CanvasProperties properties;

    @Mock
    private CourseConverter converter;

    private CoursesDataHandler handler;

    @BeforeEach
    void setup() {
        handler = new CoursesDataHandler(restTemplate, facade, properties, converter);
    }

    @Test
    public void apply_NewCourse() {

        Sync sync = new Sync();
        CanvasCourseResponse canvasCourseResponse = new CanvasCourseResponse()
            .setId(1L)
            .setName("test");

        Course course = new Course()
            .setId(1L)
            .setName("test");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getCoursesRequestPath()).thenReturn(COURSES_REQUEST_PATH);
        when(restTemplate.getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasCourseResponse));
        when(converter.convert(canvasCourseResponse)).thenReturn(course);
        when(facade.getAllCoursesFromDb()).thenReturn(Collections.emptyList());

        handler.apply(sync);

        assertEquals(1, sync.getCoursesExists());
        assertEquals(1, sync.getCoursesNew());
        assertEquals(0, sync.getCoursesUpdated());
        assertEquals(0, sync.getCoursesDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getCoursesRequestPath();
        verify(restTemplate).getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH);
        verify(converter).convert(canvasCourseResponse);
        verify(facade).getAllCoursesFromDb();
        verify(facade).saveToDb(course);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_ExistsCourse() {

        Sync sync = new Sync();
        CanvasCourseResponse canvasCourseResponse = new CanvasCourseResponse()
            .setId(1L)
            .setName("test");

        Course course = new Course()
            .setId(1L)
            .setName("test");

        Course oldCourse = new Course()
            .setId(1L)
            .setName("test");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getCoursesRequestPath()).thenReturn(COURSES_REQUEST_PATH);
        when(restTemplate.getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasCourseResponse));
        when(converter.convert(canvasCourseResponse)).thenReturn(course);
        when(facade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(oldCourse));

        handler.apply(sync);

        assertEquals(1, sync.getCoursesExists());
        assertEquals(0, sync.getCoursesNew());
        assertEquals(0, sync.getCoursesUpdated());
        assertEquals(0, sync.getCoursesDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getCoursesRequestPath();
        verify(restTemplate).getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH);
        verify(converter).convert(canvasCourseResponse);
        verify(facade).getAllCoursesFromDb();
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_UpdateCourse() {

        Sync sync = new Sync();
        CanvasCourseResponse canvasCourseResponse = new CanvasCourseResponse()
            .setId(1L)
            .setName("test");

        Course course = new Course()
            .setId(1L)
            .setName("test");

        Course oldCourse = new Course()
            .setId(1L)
            .setName("test1");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getCoursesRequestPath()).thenReturn(COURSES_REQUEST_PATH);
        when(restTemplate.getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasCourseResponse));
        when(converter.convert(canvasCourseResponse)).thenReturn(course);
        when(facade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(oldCourse));

        handler.apply(sync);

        assertEquals(1, sync.getCoursesExists());
        assertEquals(0, sync.getCoursesNew());
        assertEquals(1, sync.getCoursesUpdated());
        assertEquals(0, sync.getCoursesDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getCoursesRequestPath();
        verify(restTemplate).getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH);
        verify(converter).convert(canvasCourseResponse);
        verify(facade).getAllCoursesFromDb();
        verify(facade).saveToDb(course);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_NewCourseAndDeleteOldCourse() {

        Sync sync = new Sync();
        CanvasCourseResponse canvasCourseResponse = new CanvasCourseResponse()
            .setId(1L)
            .setName("test");

        Course course = new Course()
            .setId(1L)
            .setName("test");

        Course oldCourse = new Course()
            .setId(2L)
            .setName("test1");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getCoursesRequestPath()).thenReturn(COURSES_REQUEST_PATH);
        when(restTemplate.getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasCourseResponse));
        when(converter.convert(canvasCourseResponse)).thenReturn(course);
        when(facade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(oldCourse));

        handler.apply(sync);

        assertEquals(1, sync.getCoursesExists());
        assertEquals(1, sync.getCoursesNew());
        assertEquals(0, sync.getCoursesUpdated());
        assertEquals(1, sync.getCoursesDeleted());

        verify(properties).getInstanceUrl();
        verify(properties).getCoursesRequestPath();
        verify(restTemplate).getCoursesFromCanvas(INSTANCE_URL + COURSES_REQUEST_PATH);
        verify(converter).convert(canvasCourseResponse);
        verify(facade).getAllCoursesFromDb();
        verify(facade).saveToDb(course);
        verify(facade).deleteFromDb(oldCourse);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }
}