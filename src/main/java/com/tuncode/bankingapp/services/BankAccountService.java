package com.tuncode.bankingapp.services;

import com.tuncode.bankingapp.configuration.response.BankAccountResponse;
import com.tuncode.bankingapp.configuration.response.TransactionResultResponse;

public interface BankAccountService {

    TransactionResultResponse depositToBankAccount(String owner, String accountNumber, double amount);

    TransactionResultResponse debitFromBankAccount(String owner, String accountNumber, double amount);

    BankAccountResponse getBankAccount(String accountNumber);


}
