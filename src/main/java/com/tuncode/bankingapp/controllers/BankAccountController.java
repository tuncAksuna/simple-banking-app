package com.tuncode.bankingapp.controllers;

import com.tuncode.bankingapp.configuration.response.BankAccountResponse;
import com.tuncode.bankingapp.configuration.response.TransactionResultResponse;
import com.tuncode.bankingapp.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account/v1")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/credit/{accountNumber}")
    public TransactionResultResponse depositToBankAccount(@PathVariable String accountNumber) {
        return bankAccountService.depositToBankAccount("Static owner name", accountNumber, 50);
    }

    @PostMapping("/debit/{accountNumber}")
    public TransactionResultResponse debitFromBankAccount(@PathVariable String accountNumber) {
        return bankAccountService.debitFromBankAccount("Static owner name", accountNumber, 100);
    }

    @GetMapping("/{accountNumber}")
    public BankAccountResponse getBankAccount(@PathVariable String accountNumber) {
        return bankAccountService.getBankAccount(accountNumber);
    }

}
