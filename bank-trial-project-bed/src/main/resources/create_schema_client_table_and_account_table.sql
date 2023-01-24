CREATE SCHEMA Bank;

CREATE TABLE Bank.accounts {
    accountId INT PRIMARY KEY AUTO_INCREMENT,
    accountType VARCHAR(255) NOT NULL,
    bankName VARCHAR(255) NOT NULL,
    bankBalance double NOT NULL,
    bankCreditLimit double NOT NULL,
    bankCreditRemainingLimit double NOT NULL,
    bankCreditBalance double NOT NULL
}


CREATE TABLE Bank.clients {
    clientId INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    accountId INT,
    FOREIGN KEY (accountId) REFERENCES Bank.accounts(accountId)
}