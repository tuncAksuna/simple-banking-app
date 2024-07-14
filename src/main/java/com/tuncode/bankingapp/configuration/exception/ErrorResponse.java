package com.tuncode.bankingapp.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final String errorCode;
    private final LocalDateTime dateTime = LocalDateTime.now();

    public ErrorResponse(BaseBankingException exception) {
        this.message = exception.getMessage();
        this.errorCode = exception.getErrorDescription();
    }

}
