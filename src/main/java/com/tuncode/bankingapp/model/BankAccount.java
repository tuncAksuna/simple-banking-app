package com.tuncode.bankingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide a simple model of how bank accounts might work in an overly simplified world.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private String accountNumber;
    private double balance;

    @OneToMany(
            mappedBy = "bankAccount",
            cascade = CascadeType.ALL
    )
    private List<Transaction> transactionList = new ArrayList<>();

}
