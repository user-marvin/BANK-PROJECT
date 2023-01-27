package com.acccenture.banktrialprojectbed.controller;

import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.service.AdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/api/adminClient")
public class AdminClientController {
    @Autowired
    AdminClientService adminClientService;

    @GetMapping("/adminLogin")
    public ResponseEntity<Client> adminLogin
            (@RequestBody LoginHelper loginHelper)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminClientService.adminLogin(loginHelper));
    }

    @GetMapping("/viewAllClient")
    public ResponseEntity<List> viewAllClient(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminClientService.viewAllClient());
    }

    @GetMapping("/viewClient/{userName}")
    public ResponseEntity<Client> viewClient
            (@PathVariable String userName)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminClientService.viewSingleClient(userName));
    }

    @PostMapping("/registerClient")
    public ResponseEntity<Client> registerClient
            (@RequestBody Client client) throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminClientService.registerClient(client));
    }

    @PutMapping("/updateClient")
    public ResponseEntity<Client> updateClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminClientService.updateClient(client));
    }

    @PutMapping("/archiveClient")
    public ResponseEntity<String> archiveClient
            (@RequestBody Client client)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminClientService.archiveClient(client));
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<String> deleteClient
            (@PathVariable int id)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminClientService.deleteClient(id));
    }
}
