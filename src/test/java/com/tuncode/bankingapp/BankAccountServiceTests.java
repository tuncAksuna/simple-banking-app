package com.tuncode.bankingapp;

import com.tuncode.bankingapp.model.BankAccount;
import com.tuncode.bankingapp.model.DepositTransaction;
import com.tuncode.bankingapp.model.WithdrawalTransaction;
import com.tuncode.bankingapp.repository.BankAccountRepository;
import com.tuncode.bankingapp.repository.TransactionRepository;
import com.tuncode.bankingapp.services.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BankAccountServiceTests {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("1999-612");
        bankAccount.setBalance(1000.0);
        bankAccountRepository.save(bankAccount);
    }

    @Test
    void testDebit() {
        bankAccountService.debit("1999-612", 100.0);

        BankAccount updatedAccount = bankAccountRepository.findByAccountNumber("1999-612").orElseThrow();
        assertThat(updatedAccount.getBalance()).isEqualTo(900.0);

        WithdrawalTransaction transaction = (WithdrawalTransaction) transactionRepository.findAll().iterator().next();
        assertThat(transaction.getAmount()).isEqualTo(100.0);
        assertThat(transaction.getBalanceAfterWithdrawal()).isEqualTo(900.0);
    }

    @Test
    void testCredit() {
        // Given
        String accountNumber = "1999-612";
        double amount = 200.0;

        // When
        bankAccountService.credit(accountNumber, amount);

        // Then
        BankAccount updatedAccount = bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow();
        assertThat(updatedAccount.getBalance()).isEqualTo(1200.0);

        DepositTransaction transaction = (DepositTransaction) transactionRepository.findAll().iterator().next();
        assertAll(
                () -> assertThat(transaction.getAmount()).isEqualTo(amount),
                () -> assertThat(transaction.getBankAccount()).isEqualTo(updatedAccount),
                () -> assertThat(transaction.getBalanceBeforeDeposit()).isEqualTo(1000.0),
                () -> assertThat(transaction.getBalanceAfterDeposit()).isEqualTo(1200.0)
        );
    }

    @Test
    void testCreditAccountNotFound() {
        String accountNumber = "654321";
        double amount = 200.0;
        assertThrows(RuntimeException.class, () -> bankAccountService.credit(accountNumber, amount));
    }
}
