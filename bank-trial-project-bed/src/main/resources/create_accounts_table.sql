CREATE TABLE `bank_db`.`accounts`(
	account_id int not null auto_increment,
    account_type varchar(255) not null,
    bank_name varchar(255) not null,
    bank_balance double not null,
    bank_deposit double not null,
    bank_withdrawal double not null,
    bank_credit_limit double not null,
    bank_credit_remaining_limit double not null,
    bank_credit_balance double not null,
    bank_credit_payment double not null,
    primary key (account_id)
);
