package com.tuncode.bankingapp.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
public class WithdrawalTransaction extends Transaction {

    private final String description;
    private String referenceNumber;
    private double balanceBeforeWithdrawal;
    private double balanceAfterWithdrawal;

    public WithdrawalTransaction(double amount) {
        super(amount);
        setType("WITHDRAWAL");
        this.description = "Withdrawal transaction ! ";
    }


    @Override
    void process(BankAccount bankAccount) {
        bankAccount.debit(getAmount());
        this.balanceBeforeWithdrawal = bankAccount.getBalance();
        this.balanceAfterWithdrawal = bankAccount.getBalance() - getAmount();
    }
}
