package com.acccenture.banktrialprojectbed.service;

import com.acccenture.banktrialprojectbed.credentials.LocalRepo;
import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.repository.AccountRepo;
import com.acccenture.banktrialprojectbed.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Admin_AccountService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    User_ClientService user_clientService;
    List<Client> allClients = new ArrayList<>();
    List<BankAccount> allAccounts = new ArrayList<>();

    /*-------------------------------------------------------------------*/

    // Can only be accessed when logged in.
    public BankAccount openAccount(BankAccount bankAccount) throws BankException {
        allClients = clientRepo.findAll();
        try{
            Optional<Client> getClient = allClients.stream().filter(client -> client.getUserName().equals(LocalRepo.userName)).findFirst();
            bankAccount.setClient(getClient.get());
            return accountRepo.save(bankAccount);
        }catch (NoSuchElementException e){
            throw new BankException(BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }


    // Can only be accessed when logged in.
    public List<BankAccount> getClientAccounts() throws BankException {
        allAccounts = accountRepo.findAll();
        try {
            List<BankAccount> bankAccounts =
                    allAccounts
                            .stream()
                            .filter(accounts -> accounts.getClient().getUserName().equals(LocalRepo.userName))
                            .collect(Collectors.toList());
            return bankAccounts;
        }catch (NoSuchElementException e){
            throw new BankException(BankException.NO_ACCOUNTS_FOUND);
        }
    }


}
