package com.acccenture.banktrialprojectbed.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
@RequiredArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "archived")
    private Boolean archived;
    @Column(name = "admin_user")
    private Boolean adminUser; // 0 = Client // 1 = Admin

//    @OneToMany
//    @JoinColumn(name = "account_id")
//    @Column(name = "accountId")
//    private List<BankAccount> account;
}
