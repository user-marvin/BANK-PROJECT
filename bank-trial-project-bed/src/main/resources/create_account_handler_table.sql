CREATE TABLE `bank_db`.`account_handler` (
  `account_handler_id` INT NOT NULL,
  `account_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  PRIMARY KEY (`account_handler_id`),
  INDEX `client_id_idx` (`client_id` ASC) VISIBLE,
  INDEX `account_id_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `client_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `bank_db`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `account_id`
    FOREIGN KEY (`account_id`)
    REFERENCES `bank_db`.`bank_accounts` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );
