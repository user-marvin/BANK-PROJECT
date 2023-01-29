package com.acccenture.banktrialprojectbed.service;


import com.acccenture.banktrialprojectbed.credentials.LocalRepo;
import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.entity.Client;
import com.acccenture.banktrialprojectbed.exception.BankException;
import com.acccenture.banktrialprojectbed.finalVariables.FinalVariables;
import com.acccenture.banktrialprojectbed.helperClasses.AccountDetails;
import com.acccenture.banktrialprojectbed.repository.AccountRepo;
import com.acccenture.banktrialprojectbed.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    ClientRepo clientRepo;
    List<Client> allClients = new ArrayList<>();

    List<BankAccount> allAccounts = new ArrayList<>();

    LocalRepo localRepo = LocalRepo.getInstance();

    // Can only be accessed when logged in.
    public BankAccount openAccount(BankAccount bankAccount)
            throws BankException {
        allClients = clientRepo.findAll();
        allAccounts = accountRepo.findAll();
        final int MIN = 100000;
        final int MAX = 999999;
        try {
            Optional<Client> getClient =
                    allClients
                            .stream()
                            .filter(client -> client.getUserName()
                                    .equals(localRepo.getUserName()))
                            .findFirst();
            bankAccount.setClient(getClient.get());
            Optional<BankAccount> accountMatch;
            do {
                int accountNumber = MIN + (int)
                        (Math.random() * (MAX - MIN) + 1);
                accountMatch = allAccounts
                        .stream()
                        .filter(account -> account.getAccountNumber()
                                == accountNumber)
                        .findFirst();
                bankAccount.setAccountNumber(accountNumber);
            } while (accountMatch.isPresent());
            return accountRepo.save(bankAccount);
        }catch (NoSuchElementException e){
            throw new BankException
                    (BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }

    public BankAccount depositMoney(AccountDetails accountDetails)
            throws BankException {
        allAccounts = accountRepo.findAll();
        try{
            Optional<BankAccount> account =
                    allAccounts
                            .stream()
                            .filter(bankAccount -> bankAccount.getAccountNumber()
                                    == accountDetails.getAccountNumber()
                                    && bankAccount.getAccountPasscode()
                                    .equals(accountDetails.getAccountPasscode()))
                            .findFirst();
            account.get().setBankBalance(account.get().getBankBalance()
                    + accountDetails.getAmount());
            return accountRepo.save(account.get());
        }catch (NoSuchElementException e){
            throw new BankException(BankException.NO_ACCOUNTS_FOUND, e.getCause());
        }
    }

    public BankAccount withdrawMoney(AccountDetails accountDetails)
            throws BankException {
        allAccounts = accountRepo.findAll();
        try{
            Optional<BankAccount> account = allAccounts
                    .stream()
                    .filter(bankAccount -> bankAccount.getAccountNumber()
                            == accountDetails.getAccountNumber()
                            && bankAccount.getAccountPasscode()
                            .equals(accountDetails.getAccountPasscode()))
                    .findFirst();
            double remainingBalance = account.get().getBankBalance()
                    - accountDetails.getAmount();

            if(remainingBalance < 0){
                throw new BankException(BankException.INSUFFICIENT_BALANCE);
            }else{
                account.get().setBankBalance(remainingBalance);
            }
            return accountRepo.save(account.get());
        }catch (NoSuchElementException e){
            throw new BankException(BankException.NO_ACCOUNTS_FOUND, e.getCause());
        }
    }

    public BankAccount transferMoney(AccountDetails accountDetails)
            throws BankException{
        allAccounts = accountRepo.findAll();
        double remainingBalance;
        try{
            Optional<BankAccount> account = allAccounts
                    .stream()
                    .filter(bankAccount -> bankAccount.getAccountNumber()
                            == accountDetails.getAccountNumber()
                            && bankAccount.getAccountPasscode()
                            .equals(accountDetails.getAccountPasscode()))
                    .findFirst();
            Optional<BankAccount> receiverAccount =
                    allAccounts
                            .stream()
                            .filter(bankAccount -> bankAccount.getAccountNumber()
                                    == accountDetails.getReceiverAccountNumber()).findFirst();

            if(!account.get()
                    .getBankName()
                    .equals(receiverAccount.get()
                            .getBankName())){
                remainingBalance = account.get().getBankBalance()
                        - accountDetails.getAmount()
                        - FinalVariables.OTHERBANKTRANSFERCHARGE;
            }else{
                remainingBalance = account.get().getBankBalance()
                        - accountDetails.getAmount();
            }

            if(remainingBalance < 0){
                throw new BankException
                        (BankException.INSUFFICIENT_BALANCE_FOR_TRANSFER);
            }else{
                account.get().setBankBalance(remainingBalance);
            }
            accountRepo.save(receiverAccount.get());
            return accountRepo.save(account.get());
        }catch (NoSuchElementException e){
            throw new BankException
                    (BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }

    public BankAccount useCreditCard(AccountDetails accountDetails) throws BankException {
        allAccounts = accountRepo.findAll();
        try{
            Optional<BankAccount> bankAccount = allAccounts
                    .stream()
                    .filter(account -> account.getAccountNumber()
                            == accountDetails.getAccountNumber()
                            && account.getAccountPasscode()
                            .equals(accountDetails.getAccountPasscode()))
                    .findFirst();
            if(!bankAccount.isPresent()){
                throw new BankException(BankException.NO_ACCOUNTS_FOUND);
            }else{
                double creditRemainingLimit = bankAccount.get().getBankCreditRemainingLimit()- accountDetails.getAmountToCredit();
                bankAccount.get().setBankCreditRemainingLimit(creditRemainingLimit);
                bankAccount.get().setBankCreditBalance(accountDetails.getAmountToCredit());
                return accountRepo.save(bankAccount.get());
            }
        }catch (NoSuchElementException e){
            throw new BankException(BankException.ACCOUNT_DO_NOT_EXIST);
        }
    }

    public BankAccount payCredit(AccountDetails accountDetails) throws BankException {
        allAccounts = accountRepo.findAll();
        try{
            Optional<BankAccount> bankAccount = allAccounts
                    .stream()
                    .filter(account -> account.getAccountNumber()
                            == accountDetails.getAccountNumber()
                            && account.getAccountPasscode()
                            .equals(accountDetails.getAccountPasscode()))
                    .findFirst();
            if(!bankAccount.isPresent()){
                throw new BankException(BankException.NO_ACCOUNTS_FOUND);
            }else{
                double bankCreditBalance = bankAccount.get().getBankCreditBalance() - accountDetails.getAmount();
                double bankCreditRemainingLimit = bankAccount.get().getBankCreditRemainingLimit() + accountDetails.getAmount();
                bankAccount.get().setBankCreditRemainingLimit(bankCreditRemainingLimit);
                bankAccount.get().setBankCreditBalance(bankCreditBalance);
                return accountRepo.save(bankAccount.get());
            }
        }catch (NoSuchElementException e){
            throw new BankException(BankException.ACCOUNT_DO_NOT_EXIST);
        }
    }
}
