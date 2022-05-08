package com.securly.securlyproject.web;

import com.securly.securlyproject.data.MvcDataManager;
import com.securly.securlyproject.web.responses.MvcSectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/sections")
public class SectionController {

    private final MvcDataManager manager;

    @Autowired
    public SectionController(MvcDataManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public String getAllSections(Model model) {
        List<MvcSectionResponse> sections = manager.getAllSections();
        sections.sort(Comparator.comparing(MvcSectionResponse::getId));
        model.addAttribute("sections", sections);
        model.addAttribute("module", "sections");
        return "sections";
    }

    @GetMapping(path = "/findByCourseId/{id}")
    public String getAllSectionsByCourseId(@PathVariable("id") long courseId, Model model) {
        List<MvcSectionResponse> sections = manager.getAllSectionsByCourseId(courseId);
        sections.sort(Comparator.comparing(MvcSectionResponse::getId));
        model.addAttribute("sections", sections);
        model.addAttribute("module", "sections");
        return "sections";
    }
}
