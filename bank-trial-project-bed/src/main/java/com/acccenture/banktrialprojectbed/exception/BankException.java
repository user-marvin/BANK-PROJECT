package com.acccenture.banktrialprojectbed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExceptionClass extends Exception{

    public static final String ACCOUNT_DO_NOT_EXIST = "Account doesn't exist.";
    public static final String ACCOUNT_CREATED = "Account created successfully.";
    public static final String ACCOUNT_UPDATED = "Account has been updated.";
    public static final String ACCOUNT_DELETED = "Account has been deleted.";
    public static final String USERNAME_ALREADY_EXIST = "Account has been deleted.";

    public ExceptionClass(String message) {
        super(message);
    }

    public ExceptionClass(String message, Throwable cause) {
        super(message, cause);
    }
}
