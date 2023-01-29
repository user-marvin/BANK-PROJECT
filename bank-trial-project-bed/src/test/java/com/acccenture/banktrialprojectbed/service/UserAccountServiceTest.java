package com.acccenture.banktrialprojectbed.service;

import com.acccenture.banktrialprojectbed.credentials.LocalRepo;
import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.repository.AccountRepo;
import com.acccenture.banktrialprojectbed.repository.ClientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserAccountServiceTest {

    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserClientService userClientService;
    List<Client> allClients = new ArrayList<>();
    List<BankAccount> allAccounts = new ArrayList<>();

    LocalRepo localRepo = LocalRepo.getInstance();
    @Test
    void openAccount() throws BankException {
        LoginHelper loginHelper = new LoginHelper("marvin.villamar", "admin123");
        //before opening an account, the user needs to be signed in first before he/she can access the endpoint
        userClientService.clientLogin(loginHelper);
        allClients = clientRepo.findAll();
        allAccounts = accountRepo.findAll();
        final String TEST_EXISTING_USERNAME = "marvin.villamar";
        Optional<Client> clientToOpenAccount =
                allClients
                        .stream()
                        .findFirst()
                        .filter(client -> client.getUserName().equals(TEST_EXISTING_USERNAME));

        BankAccount newBankAccount =
                new BankAccount(
                        "000000"
                        , "Credit"
                        , "BDO"
                        , 0.0
                        , 0.0
                        , 0.0
                        , 20000.0
                        , 20000.0
                        , 0.0
                        , 0.0
                        , clientToOpenAccount.get());
        BankAccount expectedResult = newBankAccount;
        BankAccount actualResult = userAccountService.openAccount(newBankAccount);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void depositMoney() {

    }

    @Test
    void withdrawMoney() {
    }

    @Test
    void transferMoney() {
    }

    @Test
    void useCreditCard() {
    }

    @Test
    void payCredit() {
    }
}