package com.tuncode.bankingapp.model;

import com.tuncode.bankingapp.configuration.exception.InsufficientBalanceException;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Provide a simple model of how bank accounts might work in an overly simplified world.
 */
@Getter
@Setter
public class BankAccount {

    private String owner;
    private String accountNumber;
    private double balance = 1000;
    private LocalDateTime createdDate = LocalDateTime.now();
    private List<Transaction> transactionList = new ArrayList<>();

    public BankAccount(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void post(Transaction transaction) {
        transaction.process(this);
        transactionList.add(transaction);
    }

    public void debit(double amount) {
        balanceControl(amount);
        this.balance -= amount;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    private void balanceControl(double amount) {
        if (amount > this.balance) {
            throw new InsufficientBalanceException("Insufficient balance !");
        }
    }

}
