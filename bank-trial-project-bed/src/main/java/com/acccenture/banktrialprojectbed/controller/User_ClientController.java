package com.acccenture.banktrialprojectbed.controller;


import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.service.Admin_ClientService;
import com.acccenture.banktrialprojectbed.service.User_ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userClient")
public class User_ClientController {

    @Autowired
    User_ClientService user_clientService;
    @GetMapping("/clientLogin")
    public ResponseEntity<Client> clientLogin
            (@RequestBody LoginHelper loginHelper)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user_clientService.clientLogin(loginHelper));
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user_clientService.register(client));
    }

    @GetMapping("/viewClient/{userName}")
    public ResponseEntity<Client> viewClient
            (@PathVariable String userName)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user_clientService.viewClient(userName));
    }

    @PutMapping("/archiveClient")
    public ResponseEntity<Client> archiveClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user_clientService.archiveClient(client));
    }

    @PutMapping("updateAccount")
    public ResponseEntity<Client> updateClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user_clientService.updateAccount(client));
    }

    @Autowired
    Admin_ClientService admin_clientService;
    @GetMapping("/checkPassword/{password}")
    public ResponseEntity<Client> checkPassword
            (@PathVariable String password)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(admin_clientService.checkPasswordMatch(password));
    }
}
