package com.acccenture.banktrialprojectbed.entity;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private String accountType; // Savings // Current // Credit
    private String bankName;
    private double bankBalance;
    private double bankWithdrawal;
    private double bankCreditLimit;
    private double bankCreditRemainingLimit;
    private double bankCreditBalance;
    private double bankCreditPayment;
}
