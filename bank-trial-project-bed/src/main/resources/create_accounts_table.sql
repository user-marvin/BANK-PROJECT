CREATE TABLE bank_db.bank_accounts (
  account_id INT NOT NULL AUTO_INCREMENT,
  account_number DOUBLE NOT NULL,
  account_passcode INT NOT NULL,
  account_type VARCHAR(255) NOT NULL,
  bank_name VARCHAR(255) NOT NULL,
  bank_balance DOUBLE NOT NULL,
  bank_deposit DOUBLE NOT NULL,
  bank_withdrawal DOUBLE NOT NULL,
  bank_credit_limit DOUBLE NOT NULL,
  bank_credit_remaining_limit DOUBLE NOT NULL,
  bank_credit_balance DOUBLE NOT NULL,
  bank_credit_payment DOUBLE NOT NULL,
  client_id INT NOT NULL,
  PRIMARY KEY (account_id),
  FOREIGN KEY (client_id) REFERENCES clients(client_id)
);