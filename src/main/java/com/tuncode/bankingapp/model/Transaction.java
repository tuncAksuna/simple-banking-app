package com.tuncode.bankingapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString

// JOINED inheritance type was chosen to preserve data integrity and develop an expandable structure.
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_OF_TRANSACTION", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private double amount;
    private String type;
    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccount bankAccount;

    protected Transaction() {
        this.date = LocalDateTime.now();
        this.approvalCode = UUID.randomUUID().toString();
    }

}
