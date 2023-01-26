package com.acccenture.banktrialprojectbed.service;

import com.acccenture.banktrialprojectbed.credentials.LocalRepo;
import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.helperClasses.LoginHelper;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class User_ClientService {

    @Autowired
    ClientRepo clientRepo;
    List<Client> allClients = new ArrayList<>();
    @Autowired
    Admin_ClientService admin_clientService;
    public Client clientLogin(LoginHelper loginHelper) throws BankException {
        allClients = clientRepo.findAll();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LocalRepo.userName = loginHelper.getUserName();
        try{
            Optional<Client> loginClient = allClients
                    .stream()
                    .filter(client -> client.getUserName().equals(loginHelper.getUserName())
                            && passwordEncoder.matches(loginHelper.getPassword(), client.getPassword()))
                            .findFirst();
            return loginClient.get();
        }catch (NullPointerException e){
            throw new BankException
                    (BankException.USERNAME_PASSWORD_INCORRECT
                            , e.getCause());
        }
    }
    public Client register(Client client) throws BankException {
        return admin_clientService.registerClient(client);
    }

    public Client viewClient(String userName) throws BankException{
        System.out.println(LocalRepo.userName);
        return admin_clientService.viewSingleClient(userName);
    }

    public String archiveClient(Client client) throws BankException {
        return admin_clientService.archiveClient(client);
    }

    public Client updateAccount(Client client) throws BankException{
        return admin_clientService.updateClient(client);
    }

}
