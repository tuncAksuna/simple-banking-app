package com.tuncode.bankingapp.services.impl;

import com.tuncode.bankingapp.configuration.mapper.BankAccountMapper;
import com.tuncode.bankingapp.configuration.mapper.TransactionMapper;
import com.tuncode.bankingapp.configuration.response.BankAccountResponse;
import com.tuncode.bankingapp.configuration.response.TransactionResultResponse;
import com.tuncode.bankingapp.model.BankAccount;
import com.tuncode.bankingapp.model.DepositTransaction;
import com.tuncode.bankingapp.model.WithdrawalTransaction;
import com.tuncode.bankingapp.services.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This service handles bank account related tasks.
 * For example, withdrawing money, sending money to the account, etc.
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final Map<String, BankAccount> accounts = new HashMap<>();

    /**
     * The method adds the amount received as an argument to the balance of the account according to the amount received from the bank account, according to the account owner and account number.
     */
    @Override
    public TransactionResultResponse depositToBankAccount(String owner, String accountNumber, double amount) {
        BankAccount bankAccount = new BankAccount(owner, accountNumber);
        bankAccount.post(new DepositTransaction(amount));
        accounts.put(accountNumber, bankAccount);
        return new TransactionResultResponse();
    }

    /**
     * The method deducts the amount received as an argument from the account balance by the amount received from the bank account according to the account owner and account number.
     * If the amount requested to be withdrawn from the account is more than the current balance of the bank account, the "InsufficientBalance" exception will be returned.
     */
    @Override
    public TransactionResultResponse debitFromBankAccount(String owner, String accountNumber, double amount) {
        BankAccount bankAccount = new BankAccount(owner, accountNumber);
        bankAccount.post(new WithdrawalTransaction(amount));
        accounts.put(accountNumber, bankAccount);
        return new TransactionResultResponse();
    }

    /**
     * The method finds the bank account according to the account number given as an argument. If there is no such bank account, the error "No such account 'accountNumber'" returns.
     */
    @Override
    public BankAccountResponse getBankAccount(String accountNumber) {
        BankAccount bankAccount = accounts.get(accountNumber);
        if (bankAccount == null) {
            throw new IllegalArgumentException("No such account " + accountNumber);
        }
        return BankAccountMapper.MAPPER.mapToBankAccountResponseDto(bankAccount, TransactionMapper.MAPPER.mapToTransactionDto(bankAccount.getTransactionList()));
    }
}
