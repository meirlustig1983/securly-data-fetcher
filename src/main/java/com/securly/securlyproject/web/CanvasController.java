package com.securly.securlyproject.web;

import com.securly.securlyproject.log.EnableLog;
import com.securly.securlyproject.services.CanvasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasService service;

    @Autowired
    public CanvasController(CanvasService service) {
        this.service = service;
    }

    @EnableLog
    @RequestMapping("/startSync")
    public void startSync(HttpServletResponse response) throws IOException {
        if (service.validateToken()) {
            syncData(response);
        } else {
            String canvasRedirectUrl = service.getCanvasRedirectUrl();
            log.info("start authentication process via canvas API, redirect to canvas-api");
            response.sendRedirect(canvasRedirectUrl);
        }
    }

    @RequestMapping("/oauth2response")
    public void oauth2response(HttpServletResponse response, @RequestParam(required = false) String code, @RequestParam(required = false) String state) throws IOException {
        try {
            service.applyAccessTokenRequest(code);
            syncData(response);
        } catch (Exception e) {
            response.sendRedirect("/");
        }
    }

    private void syncData(HttpServletResponse response) throws IOException {
        service.syncDate();
        response.sendRedirect("/");
    }
}