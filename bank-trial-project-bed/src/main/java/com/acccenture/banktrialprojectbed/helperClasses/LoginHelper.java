package com.acccenture.banktrialprojectbed.helperClasses;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginHelper {
    private String userName;
    private String password;

    public LoginHelper(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
