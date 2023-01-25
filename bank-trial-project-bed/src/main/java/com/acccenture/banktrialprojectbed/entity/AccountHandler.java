package com.acccenture.banktrialprojectbed.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account_handler")
@RequiredArgsConstructor
@Getter
@Setter
public class AccountHandler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_handler_id")
    private int accountHandlerId;

    @OneToMany
    @JoinColumn(name = "client_id")
    @Column(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @Column(name = "account_id")
    private BankAccount bankAccount;
}
