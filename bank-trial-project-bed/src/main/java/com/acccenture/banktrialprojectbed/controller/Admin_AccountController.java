package com.acccenture.banktrialprojectbed.controller;

import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.service.Admin_AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminAccount")
public class Admin_AccountController {

    @Autowired
    Admin_AccountService admin_accountService;

    @PostMapping("/openAccount")
    public ResponseEntity<BankAccount> openAccount
            (@RequestBody BankAccount bankAccount) throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(admin_accountService.openAccount(bankAccount));
    }

    @GetMapping("/getAccounts")
    public ResponseEntity<List> getAccounts() throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(admin_accountService.getClientAccounts());
    }
}
