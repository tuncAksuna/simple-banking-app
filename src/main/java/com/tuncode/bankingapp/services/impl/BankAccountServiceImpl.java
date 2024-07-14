package com.tuncode.bankingapp.services.impl;

import com.tuncode.bankingapp.configuration.exception.InsufficientBalanceException;
import com.tuncode.bankingapp.configuration.mapper.BankAccountMapper;
import com.tuncode.bankingapp.configuration.mapper.TransactionMapper;
import com.tuncode.bankingapp.configuration.response.BankAccountResponse;
import com.tuncode.bankingapp.configuration.response.TransactionDto;
import com.tuncode.bankingapp.configuration.response.TransactionResultResponse;
import com.tuncode.bankingapp.model.BankAccount;
import com.tuncode.bankingapp.model.DepositTransaction;
import com.tuncode.bankingapp.model.WithdrawalTransaction;
import com.tuncode.bankingapp.repository.BankAccountRepository;
import com.tuncode.bankingapp.repository.TransactionRepository;
import com.tuncode.bankingapp.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * This service performs transactions related to the BankAccount object.
 * It has methods such as withdrawing money from the bank account, sending money to the bank account, and obtaining information about the bank account.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    private static final String DEBIT_TRANSACTION_REFERENCE_NUMBER_PREFIX = "90";

    /**
     * Retrieves the BankAccount object from the database based on the account number provided as an argument,
     * Compares the current balance of the incoming BankAccount object with the amount requested to be withdrawn.
     * If there is less money in the account than the amount requested to be withdrawn, it rollbacks the entire transaction and returns an InsufficientBalance exception.
     * Finally, since it is a withdrawal transaction, the WithdrawalTransaction object is also saved in the database.
     */
    @Override
    @Transactional
    public TransactionResultResponse debit(String accountNumber, double amount) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);

        balanceCheck(amount, bankAccount);

        bankAccount.setBalance(bankAccount.getBalance() - amount);
        setDebitTransaction(amount, bankAccount);
        bankAccountRepository.save(bankAccount);
        log.info("Debit transaction successful");

        return new TransactionResultResponse();
    }

    @Override
    @Transactional
    public TransactionResultResponse credit(String accountNumber, double amount) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        setDepositTransaction(amount, bankAccount);
        bankAccountRepository.save(bankAccount);
        log.info("Credit transaction successful");

        return new TransactionResultResponse();
    }

    /**
     * It retrieves the BankAccount object from the database via JPA according to the account number that comes as an argument,
     * and returns the value by equalizing it to the BankAccountResponse object.
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public BankAccountResponse getAccount(String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Data Not Found !"));

        List<TransactionDto> transactionDtos = TransactionMapper.MAPPER.mapToTransactionDto(bankAccount.getTransactionList());
        return BankAccountMapper.MAPPER.mapToBankAccountResponseDto(bankAccount, transactionDtos);
    }

    /**
     * If the amount to be withdrawn is less than the current balance, no error occurs and the amount to be withdrawn is subtracted from the balance field of the BankAccount object and saved to the database.
     */
    private static void balanceCheck(double amount, BankAccount bankAccount) {
        if (bankAccount.getBalance() < amount) {
            log.error("There is not enough balance in the account to withdraw money");
            throw new InsufficientBalanceException("Insufficient Balance !");
        }
    }

    /**
     * It sets WithdrawalTransaction object according to the amount and account information received as arguments and saves it to the database via JPA.
     */
    private void setDebitTransaction(double amount, BankAccount bankAccount) {
        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction();
        withdrawalTransaction.setBankAccount(bankAccount);
        withdrawalTransaction.setAmount(amount);
        withdrawalTransaction.setReferenceNumber(DEBIT_TRANSACTION_REFERENCE_NUMBER_PREFIX + bankAccount.getAccountNumber());
        withdrawalTransaction.setBalanceBeforeWithdrawal(bankAccount.getBalance() + amount);
        withdrawalTransaction.setBalanceAfterWithdrawal(bankAccount.getBalance());
        transactionRepository.save(withdrawalTransaction);
    }

    /**
     * It sets DepositTransaction object according to the amount and account information received as arguments and saves it to the database via JPA.
     */
    private void setDepositTransaction(double amount, BankAccount bankAccount) {
        DepositTransaction depositTransaction = new DepositTransaction();
        depositTransaction.setAmount(amount);
        depositTransaction.setBankAccount(bankAccount);
        depositTransaction.setBalanceBeforeDeposit(bankAccount.getBalance() - amount);
        depositTransaction.setBalanceAfterDeposit(bankAccount.getBalance());
        transactionRepository.save(depositTransaction);
    }

    /**
     * Returns Bankaaccount object by account number.
     * If it cannot find the BankAccount object, it throws "RuntimeException"
     */
    private BankAccount getAccountByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account Not Found !"));
    }

}
