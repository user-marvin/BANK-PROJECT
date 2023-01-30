package com.acccenture.banktrialprojectbed.controller;


import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userClient")
@CrossOrigin(origins = "http://localhost:3000")
public class UserClientController {

    @Autowired
    UserClientService userClientService;
    @PostMapping("/clientLogin")
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
