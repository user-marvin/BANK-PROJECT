package com.acccenture.banktrialprojectbed.controller;

import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.AccountDetails;
import com.acccenture.banktrialprojectbed.service.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminAccount")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminAccountController {

    @Autowired
    AdminAccountService adminAccountService;

    @PostMapping("/openAccount")
    public ResponseEntity<BankAccount> openAccount
            (@RequestBody BankAccount bankAccount) throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminAccountService.openAccount(bankAccount));
    }

    @GetMapping("/getAccounts")
    public ResponseEntity<List> getAccounts() throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminAccountService.getClientAccounts());
    }

    @DeleteMapping("closeAccount")
    public ResponseEntity<String> closeAccount
            (@RequestBody AccountDetails accountDetails)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminAccountService.closeAccount(accountDetails));
    }


}
