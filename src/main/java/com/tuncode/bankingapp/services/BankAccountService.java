package com.tuncode.bankingapp.services;

import com.tuncode.bankingapp.configuration.response.BankAccountResponse;
import com.tuncode.bankingapp.configuration.response.TransactionResultResponse;

public interface BankAccountService {

    TransactionResultResponse credit(String accountNumber, double amount);

    TransactionResultResponse debit(String accountNumber, double amount);

    BankAccountResponse getAccount(String accountNumber);

}
