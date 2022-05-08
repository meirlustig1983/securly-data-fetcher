package com.securly.securlyproject.data;

import com.securly.securlyproject.data.model.Account;
import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.data.model.Section;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.data.repository.AccountRepository;
import com.securly.securlyproject.data.repository.CourseRepository;
import com.securly.securlyproject.data.repository.SectionRepository;
import com.securly.securlyproject.data.repository.SyncRepository;
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
public class DataFacadeTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private SyncRepository syncRepository;

    private DataFacade dataFacade;

    @BeforeEach
    void setup() {
        dataFacade = new DataFacade(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void getAllAccountsFromDb() {

        Account obj = new Account()
            .setId(1L)
            .setName("test");

        when(accountRepository.findAll()).thenReturn(Collections.singletonList(obj));

        List<Account> result = dataFacade.getAllAccountsFromDb();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(accountRepository).findAll();
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void getAllCoursesFromDb() {

        Course obj = new Course()
            .setId(1L)
            .setName("test");

        when(courseRepository.findAll()).thenReturn(Collections.singletonList(obj));

        List<Course> result = dataFacade.getAllCoursesFromDb();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(courseRepository).findAll();
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void getAllCoursesByAccountIdFromDb() {

        Course obj = new Course()
            .setId(1L)
            .setName("test");

        when(courseRepository.findAllByAccountId(1L)).thenReturn(Collections.singletonList(obj));

        List<Course> result = dataFacade.getAllCoursesByAccountIdFromDb(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(courseRepository).findAllByAccountId(1L);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void getAllSectionsFromDb() {

        Section obj = new Section()
            .setId(1L)
            .setName("test");

        when(sectionRepository.findAll()).thenReturn(Collections.singletonList(obj));

        List<Section> result = dataFacade.getAllSectionsFromDb();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(sectionRepository).findAll();
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void getAllSectionsByCourseIdFromDb() {

        Section obj = new Section()
            .setId(1L)
            .setName("test");

        when(sectionRepository.findAllByCourseId(1L)).thenReturn(Collections.singletonList(obj));

        List<Section> result = dataFacade.getAllSectionsByCourseIdFromDb(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("test", result.get(0).getName());

        verify(sectionRepository).findAllByCourseId(1L);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void getAllSyncsFromDb() {

        Sync obj = new Sync()
            .setId(1L);

        when(syncRepository.findAll()).thenReturn(Collections.singletonList(obj));

        List<Sync> result = dataFacade.getAllSyncsFromDb();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());

        verify(syncRepository).findAll();
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void saveToDb_Sync() {

        Sync obj = new Sync()
            .setId(1L);

        dataFacade.saveToDb(obj);

        verify(syncRepository).save(obj);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void saveToDb_Account() {

        Account obj = new Account()
            .setId(1L)
            .setName("test");

        dataFacade.saveToDb(obj);

        verify(accountRepository).save(obj);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void saveToDb_Course() {

        Course obj = new Course()
            .setId(1L)
            .setName("test");

        dataFacade.saveToDb(obj);

        verify(courseRepository).save(obj);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void saveToDb_Section() {

        Section obj = new Section()
            .setId(1L)
            .setName("test");

        dataFacade.saveToDb(obj);

        verify(sectionRepository).save(obj);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void deleteFromDb_Account() {

        Account obj = new Account()
            .setId(1L)
            .setName("test");

        dataFacade.deleteFromDb(obj);

        verify(accountRepository).delete(obj);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void deleteFromDb_Course() {

        Course obj = new Course()
            .setId(1L)
            .setName("test");

        dataFacade.deleteFromDb(obj);

        verify(courseRepository).delete(obj);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }

    @Test
    public void deleteFromDb_Section() {

        Section obj = new Section()
            .setId(1L)
            .setName("test");

        dataFacade.deleteFromDb(obj);

        verify(sectionRepository).delete(obj);
        verifyNoMoreInteractions(accountRepository, courseRepository, sectionRepository, syncRepository);
    }
}