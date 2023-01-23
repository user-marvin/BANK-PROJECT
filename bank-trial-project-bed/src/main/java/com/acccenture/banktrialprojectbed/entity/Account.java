package com.acccenture.banktrialprojectbed.entity;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@RequiredArgsConstructor
public class Account {

    @Id
    @OneToOne
    private int accountId;
    private String accountType;
    private String bankName;
    private double bankBalance;
    private double bankCreditLimit;
    private double bankRemainingLimit;
    private double bankCreditBalance;
}
