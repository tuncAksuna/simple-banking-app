package com.tuncode.bankingapp.configuration.datainitializing;

import com.tuncode.bankingapp.model.BankAccount;
import com.tuncode.bankingapp.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class creates a BankAccount object when the application is running and saves it in the database.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DatatInitializer implements CommandLineRunner {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public void run(String... args) {
        BankAccount account1 = new BankAccount();
        account1.setOwner("Cem Tunc Aksuna");
        account1.setAccountNumber("669-7788");
        account1.setBalance(1000.00);

        BankAccount account2 = new BankAccount();
        account2.setOwner("Eteration");
        account2.setAccountNumber("670-7700");
        account2.setBalance(2000.00);

        bankAccountRepository.save(account1);
        bankAccountRepository.save(account2);

        log.info("Sample Data Created.");
    }
}
