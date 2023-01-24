package com.acccenture.banktrialprojectbed.controller;


import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.service.Admin_ClientService;
import com.acccenture.banktrialprojectbed.service.User_ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class User_ClientController {

    @Autowired
    User_ClientService user_clientService;

    @Autowired
    Admin_ClientService admin_clientService;

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(user_clientService.register(client));
    }

    @GetMapping("/viewClient/{userName}")
    public ResponseEntity<Client> viewClient(@PathVariable String userName) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(user_clientService.viewClient(userName));
    }
    @GetMapping("/checkPassword/{password}")
    public ResponseEntity<Boolean> checkPassword(@PathVariable String password) throws BankException {
        return ResponseEntity.status(HttpStatus.OK).body(admin_clientService.checkPasswordMatch(password));
    }
}
