package com.acccenture.banktrialprojectbed.controller;

import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.service.AdminClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adminClient")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminClientController {
    @Autowired
    AdminClientService adminClientService;
    private static final Logger log = LoggerFactory.getLogger(AdminClientController.class);
    @PostMapping("/adminLogin")
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

    @PutMapping("/archiveClient/{id}")
    public ResponseEntity<String> archiveClient
            (@PathVariable int id)
            throws BankException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminClientService.archiveClient(id));
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
