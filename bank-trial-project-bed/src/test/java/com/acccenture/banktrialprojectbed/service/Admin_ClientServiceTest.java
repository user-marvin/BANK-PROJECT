package com.acccenture.banktrialprojectbed.service;

import com.acccenture.banktrialprojectbed.credentials.LocalRepo;
import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.repository.AccountRepo;
import com.acccenture.banktrialprojectbed.repository.ClientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Admin_ClientServiceTest {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    AccountRepo accountRepo;
    @Autowired
    Admin_ClientService admin_clientService;
    List<Client> allClients = new ArrayList<>();

    @Test
    void adminLogin() throws BankException {
        allClients = clientRepo.findAll();
        LoginHelper loginHelper = new LoginHelper();
        loginHelper.setUserName("marvin.villamar");
        loginHelper.setPassword("admin123");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<Client> clientAccount =
                allClients
                        .stream()
                        .filter(client -> client.getUserName().equals(loginHelper.getUserName())
                                && passwordEncoder.matches(loginHelper.getPassword(), client.getPassword())).findFirst();

        Client expectedClient = clientAccount.get();
        Client actualClient = admin_clientService.adminLogin(loginHelper);
        assertEquals(expectedClient, actualClient);
    }

    @Test
    void viewAllClient() throws BankException {
        accountRepo.deleteAll();
        clientRepo.deleteAll();

        List<Client> client = new ArrayList<>();
        client.add(new Client(
                "Marvin Villamar",
                "Guiguinto, Bulacan",
                "marvin.villamar@accenture.com",
                LocalDate.of(1999, 9, 14),
                "marvin.villamar",
                "admin123",
                false,
                true
        ));
        client.add(new Client(
                "Harvey Specter",
                "New York",
                "harvey.specter@pearson_hard.com",
                LocalDate.of(1980, 1, 14),
                "harvey.specter",
                "harveyspecter",
                false,
                false
        ));
        clientRepo.saveAll(client);
        List<Client> expectedAllClients = new ArrayList<>(client);
        List<Client> actualAllClients = new ArrayList<>(clientRepo.findAll());
        assertEquals(expectedAllClients, actualAllClients);
    }

    @Test
    void viewSingleClient() throws BankException {
        allClients = clientRepo.findAll();
        Client expectedClient = new Client(
                "Marvin Villamar",
                "Guiguinto, Bulacan",
                "marvin.villamar@accenture.com",
                LocalDate.of(1999, 9, 14),
                "marvin.villamar",
                "admin123",
                false,
                true
        );

        Client actualClient = admin_clientService.viewSingleClient(expectedClient.getUserName());

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void registerClient() {
        Client expectedClient = new Client(
                "Michael Ross",
                "New York",
                "mike.ross@pearson_hardman.com",
                LocalDate.of(1993, 1, 28),
                "mike.ross",
                "mikeross",
                false,
                false
        );
        Client actualClient = clientRepo.save(expectedClient);
        assertEquals(expectedClient, actualClient);
    }

    @Test
    void updateClient() throws BankException {
        allClients = clientRepo.findAll();
        LocalRepo.userName = "mike.ross";
        Optional<Client> updatedClient =
                allClients
                        .stream()
                        .filter(client -> client.getUserName().equals(LocalRepo.userName)).findFirst();
        updatedClient.get().setFullName("Michael Ross");
        updatedClient.get().setEmail("mike.ross@pearson_hardman.com");

        Client actualClient = admin_clientService.updateClient(updatedClient.get());

        assertEquals(updatedClient.get(), actualClient);
    }

    @Test
    void archiveClient() throws BankException {
        allClients = clientRepo.findAll();
        LocalRepo.userName = "marvin.villamar";
        Optional<Client> expectedClient =
                allClients
                        .stream()
                        .filter(client -> client.getUserName().equals(LocalRepo.userName)).findFirst();
        expectedClient.get().setArchived(true);

        Optional<Client> findClient =
                allClients
                        .stream()
                        .filter(client -> client.getUserName().equals(LocalRepo.userName)).findFirst();
        clientRepo.save(findClient.get());

        String actualClient = admin_clientService.archiveClient(findClient.get());

        assertEquals(BankException.ACCOUNT_ARCHIVED, actualClient);
    }

    @Test
    void deleteClient() throws BankException {
        allClients = clientRepo.findAll();
        LocalRepo.userName = "marvin.villamar";
        Optional<Client> clientToDelete =
                allClients
                        .stream()
                        .filter(client -> client.getUserName().equals(LocalRepo.userName))
                        .findFirst();
        assertEquals(BankException.ACCOUNT_DEACTIVATED, admin_clientService.deleteClient(clientToDelete.get().getClientId()));
    }
}