package com.acccenture.banktrialprojectbed.controller;


import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/userClient")
public class UserClientController {

    @Autowired
    UserClientService userClientService;
    @GetMapping("/clientLogin")
    public ResponseEntity<Client> clientLogin
            (@RequestBody LoginHelper loginHelper)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userClientService.clientLogin(loginHelper));
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userClientService.register(client));
    }

    @GetMapping("/viewClient/{userName}")
    public ResponseEntity<Client> viewClient
            (@PathVariable String userName)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userClientService.viewClient(userName));
    }

    @PutMapping("/archiveClient")
    public ResponseEntity<String> archiveClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userClientService.archiveClient(client));
    }

    @PutMapping("updateAccount")
    public ResponseEntity<Client> updateClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userClientService.updateAccount(client));
    }

}
