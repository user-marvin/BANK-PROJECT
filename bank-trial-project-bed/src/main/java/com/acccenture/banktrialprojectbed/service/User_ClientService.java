package com.acccenture.banktrialprojectbed.service;

import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class User_ClientService {

    @Autowired
    ClientRepo clientRepo;
    List<Client> allClients = new ArrayList<>();
    @Autowired
    Admin_ClientService admin_clientService;
    public Client login(String userName, String password) throws BankException {
        allClients = clientRepo.findAll();
        try{
            Stream<Client> matchedClient = allClients
                    .stream()
                    .filter(
                            client -> client.getUserName().equals(userName)
                                    && client.getPassword().equals(password));
            return (Client) matchedClient;
        }catch (NullPointerException e){
            throw new BankException(BankException.USERNAME_PASSWORD_INCORRECT);
        }
    }
    public Client register(Client client) throws BankException {
        return admin_clientService.registerClient(client);
    }

    public Client viewClient(String userName) throws BankException{
        return admin_clientService.viewSingleClient(userName);
    }

    public String archiveClient(Client client) throws BankException {
        return admin_clientService.archiveClient(client);
    }

    public Client updateAccount(Client client) throws BankException{
        return admin_clientService.updateClient(client);
    }

}
