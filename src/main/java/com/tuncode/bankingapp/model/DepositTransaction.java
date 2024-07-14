package com.tuncode.bankingapp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@DiscriminatorValue(value = "DEPOSIT")
public class DepositTransaction extends Transaction {

    private double balanceAfterDeposit;
    private double balanceBeforeDeposit;
    private final String currency;

    public DepositTransaction() {
        setType("DEPOSIT");
        this.currency = "TR";
    }

}
