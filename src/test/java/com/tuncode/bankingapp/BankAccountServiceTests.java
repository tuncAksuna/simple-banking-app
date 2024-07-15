package com.tuncode.bankingapp;

import com.tuncode.bankingapp.model.BankAccount;
import com.tuncode.bankingapp.model.DepositTransaction;
import com.tuncode.bankingapp.model.PhoneBillPaymentTransaction;
import com.tuncode.bankingapp.model.WithdrawalTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BankAccountServiceTests {

    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount("Eteration", "1999-612");
    }

    @Test
    void testAll() {
        bankAccount.setBalance(5000);
        bankAccount.post(new DepositTransaction(100));
        bankAccount.post(new WithdrawalTransaction(200));
        bankAccount.post(new PhoneBillPaymentTransaction(100, "5423345566", "Turkcell"));
        assertEquals(bankAccount.getBalance(), 4800, 0.0001);

    }

}
