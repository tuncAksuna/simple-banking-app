package com.tuncode.bankingapp.model;


import lombok.*;

@Getter
@Setter
@ToString
public class DepositTransaction extends Transaction {

    private double balanceAfterDeposit;
    private double balanceBeforeDeposit;
    private final String currency;

    public DepositTransaction(double amount) {
        super(amount);
        setType("DEPOSIT");
        this.currency = "TR";
    }

    @Override
    void process(BankAccount bankAccount) {
        bankAccount.credit(getAmount());
        this.balanceBeforeDeposit = bankAccount.getBalance();
        this.balanceAfterDeposit = bankAccount.getBalance() + getAmount();
    }
}
