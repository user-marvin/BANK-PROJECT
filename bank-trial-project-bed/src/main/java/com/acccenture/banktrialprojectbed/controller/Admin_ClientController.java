package com.acccenture.banktrialprojectbed.controller;

import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.service.Admin_ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminClient")
public class Admin_ClientController {
    @Autowired
    Admin_ClientService admin_clientService;

    @GetMapping("/adminLogin")
    public ResponseEntity<Client> adminLogin
            (@RequestBody LoginHelper loginHelper) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(admin_clientService.adminLogin(loginHelper));
    }

    @GetMapping("/viewAllClient")
    public ResponseEntity<List> viewAllClient(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(admin_clientService.viewAllClient());
    }

    @GetMapping("/viewClient/{userName}")
    public ResponseEntity<Client> viewClient
            (@PathVariable String userName)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(admin_clientService.viewSingleClient(userName));
    }

    @PostMapping("/registerClient")
    public ResponseEntity<Client> registerClient
            (@RequestBody Client client) throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(admin_clientService.registerClient(client));
    }

    @PutMapping("/updateClient")
    public ResponseEntity<Client> updateClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(admin_clientService.updateClient(client));
    }

    @PutMapping("/archiveClient")
    public ResponseEntity<String> archiveClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(admin_clientService.archiveClient(client));
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<String> deleteClient
            (@PathVariable int id)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(admin_clientService.deleteClient(id));
    }
}
