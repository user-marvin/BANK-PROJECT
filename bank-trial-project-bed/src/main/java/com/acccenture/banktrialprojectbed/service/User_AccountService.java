package com.acccenture.banktrialprojectbed.service;


import com.acccenture.banktrialprojectbed.entity.BankAccount;
import com.acccenture.banktrialprojectbed.exception.BankException;
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
public class User_AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    Admin_AccountService admin_accountService;

    List<BankAccount> allAccounts = new ArrayList<>();
    @Autowired
    private ClientRepo clientRepo;

    // Can only be accessed when logged in.
    public BankAccount openAccount(BankAccount bankAccount) throws BankException {
        return admin_accountService.openAccount(bankAccount);
    }
    public BankAccount depositMoney(AccountDetails accountDetails) throws BankException {
        allAccounts = accountRepo.findAll();
        try{
            Optional<BankAccount> account =
                    allAccounts
                            .stream()
                            .filter(bankAccount -> bankAccount.getAccountNumber() == accountDetails.getAccountNumber()
                                    && bankAccount.getAccountPasscode() == accountDetails.getAccountPasscode())
                            .findFirst();
            account.get().setBankBalance(account.get().getBankBalance()+ accountDetails.getAmount());
            return account.get();
        }catch (NoSuchElementException e){
            throw new BankException(BankException.NO_ACCOUNTS_FOUND, e.getCause());
        }
    }

    public BankAccount withdrawMoney(AccountDetails accountDetails) throws BankException {
        allAccounts = accountRepo.findAll();
        try{
            Optional<BankAccount> account = allAccounts
                    .stream()
                    .filter(bankAccount -> bankAccount.getAccountNumber() == accountDetails.getAccountNumber()
                            && bankAccount.getAccountPasscode() == accountDetails.getAccountPasscode())
                    .findFirst();
            double remainingBalance = account.get().getBankBalance() - accountDetails.getAmount();

            if(remainingBalance < 0){
                throw new BankException(BankException.INSUFFICIENT_BALANCE);
            }else{
                account.get().setBankBalance(remainingBalance);
            }
            return account.get();
        }catch (NoSuchElementException e){
            throw new BankException(BankException.NO_ACCOUNTS_FOUND, e.getCause());
        }
    }

    public BankAccount transferMoney(AccountDetails accountDetails) throws BankException{
        allAccounts = accountRepo.findAll();
        try{
            Optional<BankAccount> account = allAccounts
                    .stream()
                    .filter(bankAccount -> bankAccount.getAccountNumber() == accountDetails.getAccountNumber()
                            && bankAccount.getAccountPasscode() == accountDetails.getAccountPasscode())
                    .findFirst();
            double remainingBalance = account.get().getBankBalance() - accountDetails.getAmount();

            if(remainingBalance < 0){
                throw new BankException(BankException.INSUFFICIENT_BALANCE_FOR_TRANSFER);
            }else{
                account.get().setBankBalance(remainingBalance);
            }
            return account.get();
        }catch (NoSuchElementException e){
            throw new BankException(BankException.ACCOUNT_DO_NOT_EXIST, e.getCause());
        }
    }



}
