package com.securly.securlyproject.data;

import com.securly.securlyproject.converters.AccountConverter;
import com.securly.securlyproject.converters.CourseConverter;
import com.securly.securlyproject.converters.SectionConverter;
import com.securly.securlyproject.data.model.Account;
import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.data.model.Section;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.web.responses.MvcAccountResponse;
import com.securly.securlyproject.web.responses.MvcCourseResponse;
import com.securly.securlyproject.web.responses.MvcSectionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class MvcDataManagerTest {

    @Mock
    private DataFacade dataFacade;

    @Mock
    private AccountConverter accountConverter;

    @Mock
    private CourseConverter courseConverter;

    @Mock
    private SectionConverter sectionConverter;

    private MvcDataManager manager;

    @BeforeEach
    void setup() {
        manager = new MvcDataManager(dataFacade, accountConverter, courseConverter, sectionConverter);
    }

    @Test
    public void getAllAccounts() {

        Account account = new Account()
            .setId(1L)
            .setName("test");

        MvcAccountResponse mvcAccountResponse = new MvcAccountResponse()
            .setId(1L)
            .setName("test");

        when(dataFacade.getAllAccountsFromDb()).thenReturn(Collections.singletonList(account));
        when(accountConverter.convert(account)).thenReturn(mvcAccountResponse);

        List<MvcAccountResponse> result = manager.getAllAccounts();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(dataFacade).getAllAccountsFromDb();
        verify(accountConverter).convert(account);
        verifyNoMoreInteractions(dataFacade, accountConverter, courseConverter, sectionConverter);
    }

    @Test
    public void getAllCourses() {

        Course course = new Course()
            .setId(1L)
            .setName("test");

        MvcCourseResponse mvcCourseResponse = new MvcCourseResponse()
            .setId(1L)
            .setName("test");

        when(dataFacade.getAllCoursesFromDb()).thenReturn(Collections.singletonList(course));
        when(courseConverter.convert(course)).thenReturn(mvcCourseResponse);

        List<MvcCourseResponse> result = manager.getAllCourses();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(dataFacade).getAllCoursesFromDb();
        verify(courseConverter).convert(course);
        verifyNoMoreInteractions(dataFacade, accountConverter, courseConverter, sectionConverter);
    }

    @Test
    public void getAllCoursesByAccountId() {

        Course course = new Course()
            .setId(1L)
            .setName("test")
            .setAccountId(1L);

        MvcCourseResponse mvcCourseResponse = new MvcCourseResponse()
            .setId(1L)
            .setName("test")
            .setAccountId(1L);

        when(dataFacade.getAllCoursesByAccountIdFromDb(1L)).thenReturn(Collections.singletonList(course));
        when(courseConverter.convert(course)).thenReturn(mvcCourseResponse);

        List<MvcCourseResponse> result = manager.getAllCoursesByAccountId(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());
        assertEquals(1L, result.get(0).getAccountId());

        verify(dataFacade).getAllCoursesByAccountIdFromDb(1L);
        verify(courseConverter).convert(course);
        verifyNoMoreInteractions(dataFacade, accountConverter, courseConverter, sectionConverter);
    }

    @Test
    public void getAllSections() {

        Section section = new Section()
            .setId(1L)
            .setName("test");

        MvcSectionResponse mvcSectionResponse = new MvcSectionResponse()
            .setId(1L)
            .setName("test");

        when(dataFacade.getAllSectionsFromDb()).thenReturn(Collections.singletonList(section));
        when(sectionConverter.convert(section)).thenReturn(mvcSectionResponse);

        List<MvcSectionResponse> result = manager.getAllSections();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(dataFacade).getAllSectionsFromDb();
        verify(sectionConverter).convert(section);
        verifyNoMoreInteractions(dataFacade, accountConverter, courseConverter, sectionConverter);
    }

    @Test
    public void getAllSectionsByCourseId() {

        Section section = new Section()
            .setId(1L)
            .setName("test")
            .setCourseId(1L);

        MvcSectionResponse mvcSectionResponse = new MvcSectionResponse()
            .setId(1L)
            .setName("test")
            .setCourseId(1L);

        when(dataFacade.getAllSectionsByCourseIdFromDb(1L)).thenReturn(Collections.singletonList(section));
        when(sectionConverter.convert(section)).thenReturn(mvcSectionResponse);

        List<MvcSectionResponse> result = manager.getAllSectionsByCourseId(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());
        assertEquals(1L, result.get(0).getCourseId());

        verify(dataFacade).getAllSectionsByCourseIdFromDb(1L);
        verify(sectionConverter).convert(section);
        verifyNoMoreInteractions(dataFacade, accountConverter, courseConverter, sectionConverter);
    }

    @Test
    public void getAllSyncs() {

        Sync sync = new Sync()
            .setId(1L);

        when(dataFacade.getAllSyncsFromDb()).thenReturn(Collections.singletonList(sync));

        List<Sync> result = manager.getAllSyncs();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());

        verify(dataFacade).getAllSyncsFromDb();
        verifyNoMoreInteractions(dataFacade, accountConverter, courseConverter, sectionConverter);
    }
}