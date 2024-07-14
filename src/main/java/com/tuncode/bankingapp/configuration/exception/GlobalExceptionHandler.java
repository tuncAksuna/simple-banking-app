package com.tuncode.bankingapp.configuration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseBankingException.class)
    public ResponseEntity<ErrorResponse> baseBankingException(BaseBankingException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> insufficientBalanceException(InsufficientBalanceException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generalException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("AN ERROR OCCURED !", "INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
