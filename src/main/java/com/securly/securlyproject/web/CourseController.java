package com.securly.securlyproject.web;

import com.securly.securlyproject.data.MvcDataManager;
import com.securly.securlyproject.web.responses.MvcCourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final MvcDataManager manager;

    @Autowired
    public CourseController(MvcDataManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public String getAllCourses(Model model) {
        List<MvcCourseResponse> courses = manager.getAllCourses();
        courses.sort(Comparator.comparing(MvcCourseResponse::getId));
        model.addAttribute("courses", courses);
        model.addAttribute("module", "courses");
        return "courses";
    }

    @GetMapping(path = "/findByAccountId/{id}")
    public String getAllCoursesByAccountId(@PathVariable("id") long accountId, Model model) {
        List<MvcCourseResponse> courses = manager.getAllCoursesByAccountId(accountId);
        courses.sort(Comparator.comparing(MvcCourseResponse::getId));
        model.addAttribute("courses", courses);
        model.addAttribute("module", "courses");
        return "courses";
    }
}
