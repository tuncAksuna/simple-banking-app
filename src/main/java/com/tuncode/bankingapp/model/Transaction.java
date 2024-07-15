package com.tuncode.bankingapp.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public abstract class Transaction {

    private final LocalDateTime date;
    private double amount;
    private String type;
    private final String approvalCode;

    protected Transaction(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.approvalCode = UUID.randomUUID().toString();
    }

    abstract void process(BankAccount bankAccount);

}
