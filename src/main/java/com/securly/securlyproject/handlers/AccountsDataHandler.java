package com.securly.securlyproject.handlers;

import com.securly.securlyproject.config.CanvasProperties;
import com.securly.securlyproject.converters.AccountConverter;
import com.securly.securlyproject.data.DataFacade;
import com.securly.securlyproject.data.model.Account;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import com.securly.securlyproject.security.oauth2.respones.CanvasAccountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Order(1)
@Component
public class AccountsDataHandler implements DataHandler {

    private final OAuth2RestTemplate restTemplate;

    private final DataFacade facade;

    private final CanvasProperties properties;

    private final AccountConverter converter;

    @Autowired
    public AccountsDataHandler(OAuth2RestTemplate restTemplate, DataFacade facade, CanvasProperties properties, AccountConverter converter) {
        this.restTemplate = restTemplate;
        this.facade = facade;
        this.properties = properties;
        this.converter = converter;
    }

    @Override
    public void apply(Sync sync) {
        List<CanvasAccountResponse> accountsFromCanvas = restTemplate.getAccountsFromCanvas(properties.getInstanceUrl() + properties.getAccountsRequestPath());
        Set accountsFromCanvasIds = accountsFromCanvas.stream().map(CanvasAccountResponse::getId).collect(Collectors.toSet());
        log.info("accounts objects received from canvas-api, data: {}", accountsFromCanvas);
        List<CanvasAccountResponse> courseAccountsFromCanvas = restTemplate.getAccountsFromCanvas(properties.getInstanceUrl() + properties.getCourseAccountsRequestPath())
            .stream().filter(canvasAccountResponse -> !accountsFromCanvasIds.contains(canvasAccountResponse.getId())).collect(Collectors.toList());
        log.info("course accounts objects received from canvas-api, data: {}", courseAccountsFromCanvas);

        List<CanvasAccountResponse> accounts = Stream.concat(accountsFromCanvas.stream(), courseAccountsFromCanvas.stream())
            .collect(Collectors.toList());

        Map<Long, Account> accountsFromDb = facade.getAllAccountsFromDb().stream().collect(Collectors.toMap(Account::getId, Function.identity()));

        sync.setAccountsExists(accounts.size());

        accounts.forEach(canvasAccountResponse -> {
            Account account = converter.convert(canvasAccountResponse);
            if (accountsFromDb.containsKey(account.getId())) {
                log.info("account already exists, account-id: {}, account-name: {}", canvasAccountResponse.getId(), canvasAccountResponse.getName());
                Account oldAccount = accountsFromDb.get(account.getId());
                if (!oldAccount.equals(account)) {
                    log.info("account has been updated, account-id: {}, account-name: {}", canvasAccountResponse.getId(), canvasAccountResponse.getName());
                    facade.saveToDb(account);
                    sync.setAccountsUpdated(sync.getAccountsUpdated() + 1);
                }
                accountsFromDb.remove(account.getId());
            } else {
                log.info("new account has been saved, account-id: {}, account-name: {}", canvasAccountResponse.getId(), canvasAccountResponse.getName());
                facade.saveToDb(account);
                sync.setAccountsNew(sync.getAccountsNew() + 1);
            }
        });


        if (accountsFromDb.isEmpty()) {
            sync.setAccountsDeleted(0);
        } else {
            accountsFromDb.values().forEach(facade::deleteFromDb);
            sync.setAccountsDeleted(accountsFromDb.size());
        }
    }
}