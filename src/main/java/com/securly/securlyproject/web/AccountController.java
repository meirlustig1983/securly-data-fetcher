package com.securly.securlyproject.web;

import java.util.Comparator;
import java.util.List;

import com.securly.securlyproject.data.MvcDataManager;
import com.securly.securlyproject.web.responses.MvcAccountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final MvcDataManager manager;

    @Autowired
    public AccountController(MvcDataManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public String getAllAccounts(Model model) {
        List<MvcAccountResponse> accounts = manager.getAllAccounts();
        accounts.sort(Comparator.comparing(MvcAccountResponse::getId));
        model.addAttribute("accounts", accounts);
        model.addAttribute("module", "accounts");
        return "accounts";
    }
}
