package com.securly.securlyproject.converters;

import com.securly.securlyproject.data.model.Account;
import com.securly.securlyproject.security.oauth2.respones.CanvasAccountResponse;
import com.securly.securlyproject.web.responses.MvcAccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    public MvcAccountResponse convert(Account account) {
        return new MvcAccountResponse()
            .setId(account.getId())
            .setName(account.getName())
            .setDefaultTimeZone(account.getDefaultTimeZone())
            .setWorkflowState(account.getWorkflowState());
    }

    public Account convert(CanvasAccountResponse canvasAccountResponse) {
        return new Account()
            .setId(canvasAccountResponse.getId())
            .setName(canvasAccountResponse.getName())
            .setWorkflowState(canvasAccountResponse.getWorkflowState())
            .setParentAccountId(canvasAccountResponse.getParentAccountId())
            .setRootAccountId(canvasAccountResponse.getRootAccountId())
            .setUuid(canvasAccountResponse.getUuid())
            .setDefaultStorageQuotaMb(canvasAccountResponse.getDefaultStorageQuotaMb())
            .setDefaultUserStorageQuotaMb(canvasAccountResponse.getDefaultUserStorageQuotaMb())
            .setDefaultGroupStorageQuotaMb(canvasAccountResponse.getDefaultGroupStorageQuotaMb())
            .setDefaultTimeZone(canvasAccountResponse.getDefaultTimeZone());
    }
}
