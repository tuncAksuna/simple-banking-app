package com.tuncode.bankingapp.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TransactionDto {

    private LocalDateTime date;
    private double amount;
    private String type;
    private String approvalCode;

}
