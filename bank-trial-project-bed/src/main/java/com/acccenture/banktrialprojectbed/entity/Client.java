package com.acccenture.banktrialprojectbed.entity;


import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    private String fullName;
    private String address;
    private String email;
    private String userName;
    private String password;

    @OneToMany
    @JoinColumn(name = "accountId")
    private Account account;
}
