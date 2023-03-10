package com.acccenture.banktrialprojectbed.service;

import com.acccenture.banktrialprojectbed.credentials.LocalRepo;
import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
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
class User_ClientServiceTest {

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    User_ClientService user_clientService;
    List<Client> allClients = new ArrayList<>();


    @Test
    void clientLogin() throws BankException {
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
        Client actualClient = user_clientService.clientLogin(loginHelper);
        assertEquals(expectedClient, actualClient);
    }

    @Test
    void register() throws BankException {
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
        Client actualClient = user_clientService.register(expectedClient);
        assertEquals(expectedClient, actualClient);
    }

    @Test
    void viewClient() throws BankException {
        allClients = clientRepo.findAll();
        LocalRepo.userName = "marvin.villamar";
        Optional<Client> expectedClient =
                allClients
                        .stream()
                        .filter(client -> client.getUserName().equals(LocalRepo.userName)).findFirst();

        Client actualClient = user_clientService.viewClient(LocalRepo.userName);

        assertEquals(expectedClient.get(), actualClient);
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

        String actualClient = user_clientService.archiveClient(findClient.get());

        assertEquals(BankException.ACCOUNT_ARCHIVED, actualClient);
    }

    @Test
    void updateAccount() throws BankException {
        allClients = clientRepo.findAll();
        LocalRepo.userName = "mike.ross";
        Optional<Client> updatedClient =
                allClients
                        .stream()
                        .filter(client -> client.getUserName().equals(LocalRepo.userName)).findFirst();
        updatedClient.get().setFullName("Michael Ross");
        updatedClient.get().setEmail("mike.ross@pearson_hardman.com");

        Client actualClient = user_clientService.updateAccount(updatedClient.get());

        assertEquals(updatedClient.get(), actualClient);
    }
}