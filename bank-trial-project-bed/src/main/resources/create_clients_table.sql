CREATE TABLE clients(
	clientId int not null auto_increment,
    fullName varchar(255) not null,
    address varchar(255) not null,
    email varchar(255) not null,
    DOB date not null,
    userName varchar(255) not null,
    password varchar(255) not null,
    accountId int not null,
    foreign key (accountId) references accounts(accountId),
    primary key (clientId)
);