package com.tuncode.bankingapp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@DiscriminatorValue(value = "WITHDRAWAL")
public class WithdrawalTransaction extends Transaction {

    private final String description;
    private String referenceNumber;
    private double balanceBeforeWithdrawal;
    private double balanceAfterWithdrawal;

    public WithdrawalTransaction() {
        setType("WITHDRAWAL");
        this.description = "Withdrawal transaction ! ";
    }

}
