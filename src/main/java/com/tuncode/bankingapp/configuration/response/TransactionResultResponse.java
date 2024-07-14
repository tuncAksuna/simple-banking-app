package com.tuncode.bankingapp.configuration.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TransactionResultResponse {

    private final String status;
    private final String approvalCode;

    public TransactionResultResponse() {
        this.status = "OK";
        this.approvalCode = UUID.randomUUID().toString();

    }
}
