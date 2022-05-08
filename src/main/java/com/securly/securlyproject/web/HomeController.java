package com.securly.securlyproject.web;

import com.securly.securlyproject.data.MvcDataManager;
import com.securly.securlyproject.data.model.Sync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final MvcDataManager manager;

    @Autowired
    public HomeController(MvcDataManager manager) {
        this.manager = manager;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Sync> syncs = manager.getAllSyncs();
        syncs.sort(Comparator.comparing(Sync::getId));
        model.addAttribute("syncs", syncs);
        model.addAttribute("module", "syncs");
        return "index";
    }
}
