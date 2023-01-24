package com.acccenture.banktrialprojectbed.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@RequiredArgsConstructor
@Getter
@Setter
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
