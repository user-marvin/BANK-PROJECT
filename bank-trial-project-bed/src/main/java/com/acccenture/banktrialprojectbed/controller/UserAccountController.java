package com.acccenture.banktrialprojectbed.controller;

import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.AccountDetails;
import com.acccenture.banktrialprojectbed.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAccount")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;


    @PostMapping("/openAccount")
    public ResponseEntity<BankAccount> openAccount
            (@RequestBody BankAccount bankAccount)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userAccountService.openAccount(bankAccount));
    }
    @PutMapping("/deposit")
    public ResponseEntity<BankAccount> deposit
            (@RequestBody AccountDetails accountDetails)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userAccountService.depositMoney(accountDetails));
    }
    @PutMapping("/withdraw")
    public ResponseEntity<BankAccount> withdraw
            (@RequestBody AccountDetails accountDetails)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userAccountService.withdrawMoney(accountDetails));
    }
    @PutMapping("/transfer")
    public ResponseEntity<BankAccount> transfer
            (@RequestBody AccountDetails accountDetails)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userAccountService.transferMoney(accountDetails));
    }

    @PutMapping("/useCredit")
    public ResponseEntity<BankAccount> useCredit
            (@RequestBody AccountDetails accountDetails)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userAccountService.useCreditCard(accountDetails));
    }
    @PutMapping("/payCredit")
    public ResponseEntity<BankAccount> payCredit
            (@RequestBody AccountDetails accountDetails)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userAccountService.payCredit(accountDetails));
    }
}
