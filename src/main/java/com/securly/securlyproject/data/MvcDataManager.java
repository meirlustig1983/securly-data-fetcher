package com.securly.securlyproject.data;

import com.securly.securlyproject.converters.AccountConverter;
import com.securly.securlyproject.converters.CourseConverter;
import com.securly.securlyproject.converters.SectionConverter;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.web.responses.MvcAccountResponse;
import com.securly.securlyproject.web.responses.MvcCourseResponse;
import com.securly.securlyproject.web.responses.MvcSectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MvcDataManager {

    private final DataFacade dataFacade;

    private final AccountConverter accountConverter;

    private final CourseConverter courseConverter;

    private final SectionConverter sectionConverter;

    @Autowired
    public MvcDataManager(DataFacade dataFacade, AccountConverter accountConverter, CourseConverter courseConverter, SectionConverter sectionConverter) {
        this.dataFacade = dataFacade;
        this.accountConverter = accountConverter;
        this.courseConverter = courseConverter;
        this.sectionConverter = sectionConverter;
    }

    public List<MvcAccountResponse> getAllAccounts() {
        return dataFacade.getAllAccountsFromDb().stream().map(accountConverter::convert).collect(Collectors.toList());
    }

    public List<MvcCourseResponse> getAllCourses() {
        return dataFacade.getAllCoursesFromDb().stream().map(courseConverter::convert).collect(Collectors.toList());
    }

    public List<MvcCourseResponse> getAllCoursesByAccountId(long accountId) {
        return dataFacade.getAllCoursesByAccountIdFromDb(accountId).stream().map(courseConverter::convert).collect(Collectors.toList());
    }

    public List<MvcSectionResponse> getAllSections() {
        return dataFacade.getAllSectionsFromDb().stream().map(sectionConverter::convert).collect(Collectors.toList());
    }

    public List<MvcSectionResponse> getAllSectionsByCourseId(long courseId) {
        return dataFacade.getAllSectionsByCourseIdFromDb(courseId).stream().map(sectionConverter::convert).collect(Collectors.toList());
    }

    public List<Sync> getAllSyncs() {
        return dataFacade.getAllSyncsFromDb();
    }
}
