package com.acccenture.banktrialprojectbed.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "clients")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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

    public Client(String fullName, String address, String email, LocalDate birthDate, String userName, String password, Boolean archived, Boolean adminUser) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
        this.userName = userName;
        this.password = password;
        this.archived = archived;
        this.adminUser = adminUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(fullName, client.fullName) && Objects.equals(address, client.address) && Objects.equals(email, client.email) && Objects.equals(birthDate, client.birthDate) && Objects.equals(userName, client.userName) && Objects.equals(password, client.password) && Objects.equals(archived, client.archived) && Objects.equals(adminUser, client.adminUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, address, email, birthDate, userName, password, archived, adminUser);
    }
}
