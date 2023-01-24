package com.acccenture.banktrialprojectbed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BankException extends Exception{

    public static final String ACCOUNT_DO_NOT_EXIST = "Account doesn't exist.";
    public static final String USERNAME_ALREADY_EXIST = "Username already exist, please choose a unique one.";
    public static final String ACCOUNT_ARCHIVED = "Your account has been archived.";
    public static final String ACCOUNT_DEACTIVATED = "Your account has been deactivated.";
    public static final String USERNAME_PASSWORD_INCORRECT = "Username or password incorrect.";

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }
}
