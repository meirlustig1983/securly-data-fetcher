package com.securly.securlyproject.handlers;

import com.securly.securlyproject.config.CanvasProperties;
import com.securly.securlyproject.converters.AccountConverter;
import com.securly.securlyproject.data.DataFacade;
import com.securly.securlyproject.data.model.Account;
import com.securly.securlyproject.data.model.Sync;
import com.securly.securlyproject.security.oauth2.OAuth2RestTemplate;
import com.securly.securlyproject.security.oauth2.respones.CanvasAccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountsDataHandlerTest {

    private static final String INSTANCE_URL = "http://127.0.0.1";

    private static final String ACCOUNTS_REQUEST_PATH = "/api/v1/accounts";

    private static final String COURSE_ACCOUNTS_REQUEST_PATH = "/api/v1/course_accounts";

    @Mock
    private OAuth2RestTemplate restTemplate;

    @Mock
    private DataFacade facade;

    @Mock
    private CanvasProperties properties;

    @Mock
    private AccountConverter converter;

    private AccountsDataHandler handler;

    @BeforeEach
    void setup() {
        handler = new AccountsDataHandler(restTemplate, facade, properties, converter);
    }

    @Test
    public void apply() {

        Sync sync = new Sync();
        CanvasAccountResponse canvasAccountResponseA = new CanvasAccountResponse()
            .setId(1L)
            .setName("test");

        CanvasAccountResponse canvasAccountResponseB = new CanvasAccountResponse()
            .setId(2L)
            .setName("test");

        CanvasAccountResponse canvasAccountResponseC = new CanvasAccountResponse()
            .setId(3L)
            .setName("test");

        Account accountA = new Account()
            .setId(1L)
            .setName("test");

        Account accountB = new Account()
            .setId(2L)
            .setName("test");

        Account accountC = new Account()
            .setId(3L)
            .setName("test");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getAccountsRequestPath()).thenReturn(ACCOUNTS_REQUEST_PATH);
        when(properties.getCourseAccountsRequestPath()).thenReturn(COURSE_ACCOUNTS_REQUEST_PATH);
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH)).thenReturn(Arrays.asList(canvasAccountResponseA, canvasAccountResponseB));
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH)).thenReturn(Arrays.asList(canvasAccountResponseA, canvasAccountResponseC));
        when(converter.convert(canvasAccountResponseA)).thenReturn(accountA);
        when(converter.convert(canvasAccountResponseB)).thenReturn(accountB);
        when(converter.convert(canvasAccountResponseC)).thenReturn(accountC);
        when(facade.getAllAccountsFromDb()).thenReturn(Collections.emptyList());

        handler.apply(sync);

        assertEquals(3, sync.getAccountsExists());
        assertEquals(3, sync.getAccountsNew());
        assertEquals(0, sync.getAccountsUpdated());
        assertEquals(0, sync.getAccountsDeleted());

        verify(properties, times(2)).getInstanceUrl();
        verify(properties).getAccountsRequestPath();
        verify(properties).getCourseAccountsRequestPath();
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH);
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH);
        verify(converter).convert(canvasAccountResponseA);
        verify(converter).convert(canvasAccountResponseB);
        verify(converter).convert(canvasAccountResponseC);
        verify(facade).getAllAccountsFromDb();
        verify(facade).saveToDb(accountA);
        verify(facade).saveToDb(accountB);
        verify(facade).saveToDb(accountC);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_NewAccount() {

        Sync sync = new Sync();
        CanvasAccountResponse canvasAccountResponse = new CanvasAccountResponse()
            .setId(1L)
            .setName("test");

        Account account = new Account()
            .setId(1L)
            .setName("test");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getAccountsRequestPath()).thenReturn(ACCOUNTS_REQUEST_PATH);
        when(properties.getCourseAccountsRequestPath()).thenReturn(COURSE_ACCOUNTS_REQUEST_PATH);
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasAccountResponse));
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.emptyList());
        when(converter.convert(canvasAccountResponse)).thenReturn(account);
        when(facade.getAllAccountsFromDb()).thenReturn(Collections.emptyList());

        handler.apply(sync);

        assertEquals(1, sync.getAccountsExists());
        assertEquals(1, sync.getAccountsNew());
        assertEquals(0, sync.getAccountsUpdated());
        assertEquals(0, sync.getAccountsDeleted());

        verify(properties, times(2)).getInstanceUrl();
        verify(properties).getAccountsRequestPath();
        verify(properties).getCourseAccountsRequestPath();
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH);
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH);
        verify(converter).convert(canvasAccountResponse);
        verify(facade).getAllAccountsFromDb();
        verify(facade).saveToDb(account);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_ExistsAccount() {

        Sync sync = new Sync();
        CanvasAccountResponse canvasAccountResponse = new CanvasAccountResponse()
            .setId(1L)
            .setName("test");

        Account account = new Account()
            .setId(1L)
            .setName("test");

        Account oldAccount = new Account()
            .setId(1L)
            .setName("test");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getAccountsRequestPath()).thenReturn(ACCOUNTS_REQUEST_PATH);
        when(properties.getCourseAccountsRequestPath()).thenReturn(COURSE_ACCOUNTS_REQUEST_PATH);
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasAccountResponse));
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.emptyList());
        when(converter.convert(canvasAccountResponse)).thenReturn(account);
        when(facade.getAllAccountsFromDb()).thenReturn(Collections.singletonList(oldAccount));

        handler.apply(sync);

        assertEquals(1, sync.getAccountsExists());
        assertEquals(0, sync.getAccountsNew());
        assertEquals(0, sync.getAccountsUpdated());
        assertEquals(0, sync.getAccountsDeleted());

        verify(properties, times(2)).getInstanceUrl();
        verify(properties).getAccountsRequestPath();
        verify(properties).getCourseAccountsRequestPath();
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH);
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH);
        verify(converter).convert(canvasAccountResponse);
        verify(facade).getAllAccountsFromDb();
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_UpdateAccount() {

        Sync sync = new Sync();
        CanvasAccountResponse canvasAccountResponse = new CanvasAccountResponse()
            .setId(1L)
            .setName("test");

        Account account = new Account()
            .setId(1L)
            .setName("test");

        Account oldAccount = new Account()
            .setId(1L)
            .setName("test1");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getAccountsRequestPath()).thenReturn(ACCOUNTS_REQUEST_PATH);
        when(properties.getCourseAccountsRequestPath()).thenReturn(COURSE_ACCOUNTS_REQUEST_PATH);
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasAccountResponse));
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.emptyList());
        when(converter.convert(canvasAccountResponse)).thenReturn(account);
        when(facade.getAllAccountsFromDb()).thenReturn(Collections.singletonList(oldAccount));

        handler.apply(sync);

        assertEquals(1, sync.getAccountsExists());
        assertEquals(0, sync.getAccountsNew());
        assertEquals(1, sync.getAccountsUpdated());
        assertEquals(0, sync.getAccountsDeleted());

        verify(properties, times(2)).getInstanceUrl();
        verify(properties).getAccountsRequestPath();
        verify(properties).getCourseAccountsRequestPath();
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH);
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH);
        verify(converter).convert(canvasAccountResponse);
        verify(facade).getAllAccountsFromDb();
        verify(facade).saveToDb(account);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }

    @Test
    public void apply_NewAccountAndDeleteOldAccount() {

        Sync sync = new Sync();
        CanvasAccountResponse canvasAccountResponse = new CanvasAccountResponse()
            .setId(1L)
            .setName("test");

        Account account = new Account()
            .setId(1L)
            .setName("test");

        Account oldAccount = new Account()
            .setId(2L)
            .setName("test1");

        when(properties.getInstanceUrl()).thenReturn(INSTANCE_URL);
        when(properties.getAccountsRequestPath()).thenReturn(ACCOUNTS_REQUEST_PATH);
        when(properties.getCourseAccountsRequestPath()).thenReturn(COURSE_ACCOUNTS_REQUEST_PATH);
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.singletonList(canvasAccountResponse));
        when(restTemplate.getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH)).thenReturn(Collections.emptyList());
        when(converter.convert(canvasAccountResponse)).thenReturn(account);
        when(facade.getAllAccountsFromDb()).thenReturn(Collections.singletonList(oldAccount));

        handler.apply(sync);

        assertEquals(1, sync.getAccountsExists());
        assertEquals(1, sync.getAccountsNew());
        assertEquals(0, sync.getAccountsUpdated());
        assertEquals(1, sync.getAccountsDeleted());

        verify(properties, times(2)).getInstanceUrl();
        verify(properties).getAccountsRequestPath();
        verify(properties).getCourseAccountsRequestPath();
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + ACCOUNTS_REQUEST_PATH);
        verify(restTemplate).getAccountsFromCanvas(INSTANCE_URL + COURSE_ACCOUNTS_REQUEST_PATH);
        verify(converter).convert(canvasAccountResponse);
        verify(facade).getAllAccountsFromDb();
        verify(facade).saveToDb(account);
        verify(facade).deleteFromDb(oldAccount);
        verifyNoMoreInteractions(properties, restTemplate, converter, facade);
    }
}