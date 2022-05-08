package com.securly.securlyproject.services;

import com.securly.securlyproject.config.CanvasProperties;
import com.securly.securlyproject.data.DataFacade;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.handlers.DataHandler;
import com.securly.securlyproject.log.EnableLog;
import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CanvasService {

    private final DataFacade facade;

    private final OAuth2RestTemplate restTemplate;

    private final CanvasProperties properties;

    private final List<DataHandler> dataHandlers;

    @Autowired
    public CanvasService(DataFacade facade, OAuth2RestTemplate restTemplate, CanvasProperties properties, List<DataHandler> dataHandlers) {
        this.facade = facade;
        this.restTemplate = restTemplate;
        this.properties = properties;
        this.dataHandlers = dataHandlers;
    }

    @EnableLog
    public boolean validateToken() {
        return restTemplate.validateToken();
    }

    public String getCanvasRedirectUrl() {
        return String.format(properties.getRequestCanvasAccessTemplate(), properties.getInstanceUrl(), properties.getClientId(), properties.getRedirectUrl());
    }

    @EnableLog
    public void applyAccessTokenRequest(String code) {
        restTemplate.accessTokenRequest(code);
    }

    @EnableLog
    public void syncDate() {
        Sync sync = new Sync();
        dataHandlers.forEach(dataHandler -> dataHandler.apply(sync));
        facade.saveToDb(sync);
    }
}