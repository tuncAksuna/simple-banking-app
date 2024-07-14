package com.tuncode.bankingapp.configuration.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBankingException extends RuntimeException {

    protected String errorDescription = "BANKING_EXCEPTION";

    public BaseBankingException(String message) {
        super(message);
    }

}
