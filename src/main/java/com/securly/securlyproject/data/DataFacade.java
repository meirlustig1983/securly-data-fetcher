package com.securly.securlyproject.data;

import com.securly.securlyproject.data.model.Account;
import com.securly.securlyproject.data.model.Course;
import com.securly.securlyproject.data.model.Section;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.data.repository.AccountRepository;
import com.securly.securlyproject.data.repository.CourseRepository;
import com.securly.securlyproject.data.repository.SectionRepository;
import com.securly.securlyproject.data.repository.SyncRepository;
import com.securly.securlyproject.log.EnableLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataFacade {

    private final AccountRepository accountRepository;

    private final CourseRepository courseRepository;

    private final SectionRepository sectionRepository;

    private final SyncRepository syncRepository;

    @Autowired
    public DataFacade(AccountRepository accountRepository, CourseRepository courseRepository, SectionRepository sectionRepository, SyncRepository syncRepository) {
        this.accountRepository = accountRepository;
        this.courseRepository = courseRepository;
        this.sectionRepository = sectionRepository;
        this.syncRepository = syncRepository;
    }

    @EnableLog
    public List<Account> getAllAccountsFromDb() {
        return (List<Account>) accountRepository.findAll();
    }

    @EnableLog
    public List<Course> getAllCoursesFromDb() {
        return (List<Course>) courseRepository.findAll();
    }

    @EnableLog("accountId: %s")
    public List<Course> getAllCoursesByAccountIdFromDb(long accountId) {
        return courseRepository.findAllByAccountId(accountId);
    }

    @EnableLog
    public List<Section> getAllSectionsFromDb() {
        return (List<Section>) sectionRepository.findAll();
    }

    @EnableLog("courseId: %s")
    public List<Section> getAllSectionsByCourseIdFromDb(long courseId) {
        return sectionRepository.findAllByCourseId(courseId);
    }

    @EnableLog
    public List<Sync> getAllSyncsFromDb() {
        return (List<Sync>) syncRepository.findAll();
    }

    public void saveToDb(Sync sync) {
        syncRepository.save(sync);
    }

    public void saveToDb(Account account) {
        accountRepository.save(account);
    }

    public void saveToDb(Course course) {
        courseRepository.save(course);
    }

    public void saveToDb(Section section) {
        sectionRepository.save(section);
    }

    public void deleteFromDb(Account account) {
        accountRepository.delete(account);
    }

    public void deleteFromDb(Course course) {
        courseRepository.delete(course);
    }

    public void deleteFromDb(Section section) {
        sectionRepository.delete(section);
    }
}