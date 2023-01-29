package com.acccenture.banktrialprojectbed.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "bank_accounts")
@RequiredArgsConstructor
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;
    @Column(name = "account_number")
    private int accountNumber;
    @Column(name = "account_passcode")
    private String accountPasscode;

    // Savings // Current // Credit
    @Column(name = "account_type")
    private String accountType;

    // BPI // UBS // BDO // UB
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_balance")
    private double bankBalance;
    @Column(name = "bank_deposit")
    private double bankDeposit;
    @Column(name = "bank_withdrawal")
    private double bankWithdrawal;
    @Column(name = "bank_credit_limit")
    private double bankCreditLimit;
    @Column(name = "bank_credit_remaining_limit")
    private double bankCreditRemainingLimit;
    @Column(name = "bank_credit_balance")
    private double bankCreditBalance;
    @Column(name = "bank_credit_payment")
    private double bankCreditPayment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public BankAccount(
            String accountPasscode
            , String accountType
            , String bankName
            , double bankBalance
            , double bankDeposit
            , double bankWithdrawal
            , double bankCreditLimit
            , double bankCreditRemainingLimit
            , double bankCreditBalance
            , double bankCreditPayment
            , Client client) {
        this.accountPasscode = accountPasscode;
        this.accountType = accountType;
        this.bankName = bankName;
        this.bankBalance = bankBalance;
        this.bankDeposit = bankDeposit;
        this.bankWithdrawal = bankWithdrawal;
        this.bankCreditLimit = bankCreditLimit;
        this.bankCreditRemainingLimit = bankCreditRemainingLimit;
        this.bankCreditBalance = bankCreditBalance;
        this.bankCreditPayment = bankCreditPayment;
        this.client = client;
    }
}
