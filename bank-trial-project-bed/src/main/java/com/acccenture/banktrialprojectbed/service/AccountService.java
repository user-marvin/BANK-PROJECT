package com.acccenture.banktrialprojectbed.service;


import com.acccenture.banktrialprojectbed.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepo accountRepo;



}
