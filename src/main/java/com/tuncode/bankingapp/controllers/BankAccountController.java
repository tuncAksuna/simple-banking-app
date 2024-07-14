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

    @PostMapping("/credit/{accountId}")
    public TransactionResultResponse credit(@PathVariable String accountId, @RequestParam double amount) {
        return bankAccountService.credit(accountId, amount);
    }

    @PostMapping("/debit/{accountId}")
    public TransactionResultResponse debit(@PathVariable String accountId, @RequestParam double amount) {
        return bankAccountService.debit(accountId, amount);
    }

    @GetMapping("/{accountNumber}")
    public BankAccountResponse getAccount(@PathVariable String accountNumber) {
        return bankAccountService.getAccount(accountNumber);
    }

}
