package com.acccenture.banktrialprojectbed.controller;

import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.AccountDetails;
import com.acccenture.banktrialprojectbed.service.User_AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAccount")
public class User_AccountController {

    @Autowired
    User_AccountService user_accountService;


    @PostMapping("/openAccount")
    public ResponseEntity<BankAccount> openAccount(@RequestBody BankAccount bankAccount) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(user_accountService.openAccount(bankAccount));
    }
    @PutMapping("/deposit")
    public ResponseEntity<BankAccount> deposit(@RequestBody AccountDetails accountDetails) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(user_accountService.depositMoney(accountDetails));
    }
    @PutMapping("/withdraw")
    public ResponseEntity<BankAccount> withdraw(@RequestBody AccountDetails accountDetails) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(user_accountService.withdrawMoney(accountDetails));
    }
    @PutMapping("/transfer")
    public ResponseEntity<BankAccount> transfer(@RequestBody AccountDetails accountDetails) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(user_accountService.transferMoney(accountDetails));
    }
}
