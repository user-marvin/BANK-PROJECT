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
public class UserClientService {

    @Autowired
    ClientRepo clientRepo;
    List<Client> allClients = new ArrayList<>();
    @Autowired
    AdminClientService admin_clientService;

    LocalRepo localRepo = LocalRepo.getInstance();
    public Client clientLogin(LoginHelper loginHelper)
            throws BankException {
        allClients = clientRepo.findAll();
        BCryptPasswordEncoder passwordEncoder =
                new BCryptPasswordEncoder();

        try{
            Optional<Client> loginClient = allClients
                    .stream()
                    .filter(client -> client.getUserName()
                            .equals(loginHelper.getUserName())
                            && passwordEncoder.matches(loginHelper.getPassword()
                            , client.getPassword()))
                            .findFirst();
            if (loginClient.isPresent()){
                localRepo.setUserName(loginHelper.getUserName());
                return loginClient.get();
            }else{
                throw new BankException
                        (BankException.USERNAME_PASSWORD_INCORRECT);
            }
        }catch (NullPointerException e){
            throw new BankException
                    (e.getCause());
        }
    }
    public Client register(Client client) throws BankException {
        return admin_clientService.registerClient(client);
    }

    public Client viewClient(String userName) throws BankException{
//        System.out.println(localRepo.getUserName());
        return admin_clientService.viewSingleClient(userName);
    }

    public String archiveClient(int id) throws BankException {
        return admin_clientService.archiveClient(id);
    }

    public Client updateAccount(Client client) throws BankException{
        return admin_clientService.updateClient(client);
    }

}
