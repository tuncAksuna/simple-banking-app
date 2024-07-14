package com.tuncode.bankingapp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@DiscriminatorValue(value = "PHONE_BILL_PAYMENT")
public class PhoneBillPaymentTransaction extends Transaction {

    private String phoneNumber;
    private String operatorName;

    public PhoneBillPaymentTransaction() {
        setType("PHONE_BILL_PAYMENT");
    }

}
