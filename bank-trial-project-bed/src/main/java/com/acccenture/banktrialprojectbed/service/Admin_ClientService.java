package com.acccenture.banktrialprojectbed.service;

import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.entity.LoginHelper;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Admin_ClientService {

    @Autowired
    ClientRepo clientRepo;
    List<Client> allClients = new ArrayList<>();


    public Client adminLogin(LoginHelper loginHelper) throws BankException {
        allClients = clientRepo.findAll();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try{
            loginHelper.setPassword(
                    passwordEncoder
                            .encode(loginHelper.getPassword()));
            Optional<Client> loginClient =
                    allClients
                            .stream()
                            .filter(client ->
                                    client.getUserName()
                                            .equals(loginHelper.getUserName())
                                            && client.getPassword()
                                            .equals(loginHelper.getPassword()))
                            .findFirst();
            return loginClient.get();
        }catch (NoSuchElementException e){
            throw new BankException(BankException.USERNAME_PASSWORD_INCORRECT, e.getCause());
        }
    }

    // View all Client. Privilege: admin
    public List<Client> viewAllClient(){
        allClients = clientRepo.findAll();
        return clientRepo.findAll();
    }

    // View a Client. Privilege: admin, client
    public Client viewSingleClient
    (String userName)
            throws BankException {
        allClients = clientRepo.findAll();
        try{
            Optional<Client> singleClient =
                    allClients
                            .stream()
                            .filter(client -> client.getUserName()
                                    .equals(userName)).findFirst();

            return singleClient.get();
        }catch (NoSuchElementException e){
            throw new BankException
                    (BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }

    // Register a Client. Privilege: admin, client
    public Client registerClient
    (Client client)
            throws BankException {
        allClients = clientRepo.findAll();
        try{
            Optional<Client> checkingUsername = allClients
                    .stream().
                    filter(clients ->
                            clients.getUserName()
                                    != client.getUserName()).findFirst();

            // Create a BCryptoPasswordEncoder
            BCryptPasswordEncoder passwordEncoder =
                    new BCryptPasswordEncoder();
            String hashedPassword =
                    passwordEncoder.encode(client.getPassword());
            client.setPassword(hashedPassword);
            return clientRepo.save(client);
        }catch (NoSuchElementException e){
            throw new BankException
                    (BankException.USERNAME_ALREADY_EXIST, e.getCause());
        }
    }

    // Update a Client. Privilege: admin, client
    public Client updateClient(Client client)
            throws BankException{
        allClients = clientRepo.findAll();
        try{
            Optional<Client> checkingUsername = allClients
                    .stream().
                    filter(clients -> clients
                                    .getUserName()
                                    .equals(client.getUserName()))
                    .findFirst();
            return clientRepo.save(client);
        }catch(NoSuchElementException e){
            throw new BankException
                    (BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }


    // Archive a Client. Privilege: admin, client
    public Client archiveClient(Client client) throws BankException {
        allClients = clientRepo.findAll();
        try{
            Optional<Client> checkingUsername = allClients
                    .stream().
                    filter(clients ->
                            clients.getClientId()
                                    == client.getClientId())
                    .findFirst();
            Client foundClient = checkingUsername.get();
            foundClient.setArchived(true);
            return clientRepo.save(foundClient);
        }catch (NoSuchElementException e){
            throw new BankException
                    (BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }


    // Delete a Client. Privilege: admin
    public String deleteClient(int id) throws BankException {
        allClients = clientRepo.findAll();
        try{
            clientRepo.deleteById(id);
            return BankException.ACCOUNT_DEACTIVATED;
        }catch (NullPointerException e){
            throw new BankException
                    (BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }

    public Boolean checkPasswordMatch(String password){
        allClients = clientRepo.findAll();

        BCryptPasswordEncoder passwordEncoder =
                new BCryptPasswordEncoder();
        String hashedPassword =
                passwordEncoder.encode(password);

        Optional<Client> isMatch =
                allClients
                        .stream()
                        .filter(client ->
                                client.getPassword()
                                        .equals(hashedPassword))
                        .findFirst();
        return isMatch != null;
    }

}

