package com.tuncode.bankingapp.model;

import lombok.*;

@Getter
@Setter
@ToString
public class PhoneBillPaymentTransaction extends Transaction {

    private String phoneNumber;
    private String operatorName;

    public PhoneBillPaymentTransaction(double amount, String phoneNumber, String operatorName) {
        super(amount);
        this.phoneNumber = phoneNumber;
        this.operatorName = operatorName;
        setType("PHONE_BILL_PAYMENT");
    }

    @Override
    void process(BankAccount bankAccount) {
        bankAccount.debit(getAmount());
    }

}
