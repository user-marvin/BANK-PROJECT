CREATE TABLE accounts(
	accountId int not null auto_increment,
    accountType varchar(255) not null,
    bankName varchar(255) not null,
    bankBalance double not null,
    bankDeposit double not null,
    bankWithdrawal double not null,
    bankCreditLimit double not null,
    bankCreditRemainingLimit double not null,
    bankCreditBalance double not null,
    bankCreditPayment double not null,
    primary key (accountId)
);
