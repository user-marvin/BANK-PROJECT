CREATE TABLE bank_db.clients(
	client_id int not null auto_increment,
    fullname varchar(255) not null,
    address varchar(255) not null,
    email varchar(255) not null,
    birth_date date not null,
    username varchar(255) unique not null,
    password varchar(255) not null,
    account_id int not null,
    admin_user boolean not null,
    archived boolean not null,
    primary key (client_id)
);