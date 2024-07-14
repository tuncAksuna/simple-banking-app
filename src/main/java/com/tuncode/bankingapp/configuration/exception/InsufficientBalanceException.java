package com.tuncode.bankingapp.configuration.exception;

public class InsufficientBalanceException extends BaseBankingException {

    public InsufficientBalanceException(String message) {
        super(message);
        errorDescription = "INSUFFICIENT_BALANCE";
    }

}
