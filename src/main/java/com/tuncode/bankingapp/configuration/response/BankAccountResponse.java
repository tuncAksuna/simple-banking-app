package com.tuncode.bankingapp.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class BankAccountResponse {

    private String accountNumber;
    private String owner;
    private double balance;
    private LocalDateTime createdDate;
    private List<TransactionDto> transactions;

}
