package com.acccenture.banktrialprojectbed.service;


import com.acccenture.banktrialprojectbed.entity.BankAccount;
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
    AdminAccountService admin_accountService;

    List<BankAccount> allAccounts = new ArrayList<>();

    // Can only be accessed when logged in.
    public BankAccount openAccount(BankAccount bankAccount)
            throws BankException {
        return admin_accountService.openAccount(bankAccount);
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
                                    == accountDetails.getAccountPasscode())
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
                            == accountDetails.getAccountPasscode())
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
                            == accountDetails.getAccountPasscode())
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
}
